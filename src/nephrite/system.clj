(ns nephrite.system
  (:require [nephrite.handler :as handler]
            [com.stuartsierra.flow :as flow]
            [ring.adapter.jetty :as jetty]))

(def system
  (flow/flow
   :jetty ([handler port]
             (jetty/run-jetty handler {:port port :join? false}))
   :handler ([folder]
               (handler/make-handler folder))))

(defn start
  [& [options]]
  (flow/run system options))

(defn stop
  [system]
  (.stop (:jetty system)))

(comment
  (def s (start {:folder "/tmp/foo" :port 9999}))
  (stop s))
