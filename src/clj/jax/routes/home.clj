(ns jax.routes.home
  (:require
    [jax.layout :as layout]
    [jax.db.core :as db]
    [clojure.java.io :as io]
    [jax.middleware :as middleware]
    [ring.util.response]
    [ring.util.http-response :as response]
    [hiccup.core :refer :all])
  (:import (java.util UUID)))

(defn form-field [label name]
  [:div
   [:label label]
   [:input {:name name}]])

(defn home-page [_]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (html [:html
                   [:header
                    [:title "Ratemaster 2000"]]
                   [:body
                    [:h1 "Welcome to Ratemaster"]
                    [:h2 "Ratings bisher"]
                    [:ul
                     (for [{:keys [name rating comment]} (db/get-ratings)]
                       [:li
                        [:span
                         name
                         " gibt "
                         rating
                         " Punkte und sagt: "
                         comment]])]
                    [:h2 "Schreib ein neues Rating"]
                    [:form {:method "POST"
                            :action "/rate"}
                     (form-field "Name" "name")
                     (form-field "Rating" "rating")
                     (form-field "Comment" "comment")
                     [:button {:type "submit"} "Rate!"]]]])})

(defn create-rating [{:keys [params]}]
  (db/create-rating! {:id      (str (UUID/randomUUID))
                      :name    (:name params)
                      :rating  (Integer/valueOf (:rating params))
                      :comment (:comment params)})
  {:status 301
   :headers {"Location" "/"}})

(comment
  (:params req))

(defn home-routes []
  [ "" 
   {:middleware [#_middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/" {:get home-page}]
   ["/rate" {:post create-rating}]])

