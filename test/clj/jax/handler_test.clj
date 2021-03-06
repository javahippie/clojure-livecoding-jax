(ns jax.handler-test
  (:require
    [clojure.test :refer :all]
    [ring.mock.request :refer :all]
    [jax.handler :refer :all]
    [jax.middleware.formats :as formats]
    [muuntaja.core :as m]
    [mount.core :as mount]
    [hiccup.core :as hiccup]
    [jax.routes.home :as home]))

(defn parse-json [body]
  (m/decode formats/instance "application/json" body))

(use-fixtures
  :once
  (fn [f]
    (mount/start #'jax.config/env
                 #'jax.handler/app-routes)
    (f)))

(deftest test-input-form
  (let [result (home/form-field "Name" "name")
        html (hiccup/html result)]
    (is (= [:div [:label "Name"]
            [:input {:name "name"}]] result))
    (is (= "<div><label>Name</label><input name="name" /></div>" html))))

(deftest test-app

  (testing "main route"
    (let [response ((app) (request :get "/"))]
      (is (= 200 (:status response)))))

  (testing "not-found route"
    (let [response ((app) (request :get "/invalid"))]
      (is (= 404 (:status response))))))
