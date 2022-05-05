(ns jax.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[jax started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[jax has shut down successfully]=-"))
   :middleware identity})
