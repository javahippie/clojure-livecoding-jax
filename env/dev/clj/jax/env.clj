(ns jax.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [jax.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[jax started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[jax has shut down successfully]=-"))
   :middleware wrap-dev})
