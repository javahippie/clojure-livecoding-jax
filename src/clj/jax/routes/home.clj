(ns jax.routes.home
  (:require
   [jax.layout :as layout]
   [jax.db.core :as db]
   [clojure.java.io :as io]
   [jax.middleware :as middleware]
   [ring.util.response]
   [ring.util.http-response :as response]))

(defn home-page [request]
  (layout/render request "home.html"))

(defn home-routes []
  [ "" 
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/" {:get home-page}]])

