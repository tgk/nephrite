(ns nephrite.main
  (:require [clojure.string :as string]
            [clojure.java.io :as io]
            [ring.adapter.jetty :as jetty]
            [ring.util.response :as response]
            [compojure.core :refer [routes]]
            [compojure.route :as route]
            [com.stuartsierra.flow :as flow]
            [clj-jade.core :as jade]
            [cheshire.core :as cheshire])
  (:gen-class))

(defn json-data
  [folder jade-path]
  (let [data-path (format "%s/_data/%s.json"
                          folder
                          (.substring jade-path 0 (- (.length jade-path) 5)))
        file (io/file data-path)]
    (when (.exists file)
      (cheshire/parse-stream (io/reader data-path)))))

(defn haml-handler
  [folder]
  (fn [{:keys [uri] :as req}]
    (when (re-matches #".*\.jade$" uri)
      (let [jade-file (str folder "/" uri)
            jade-data (or (json-data folder uri) {})]
        (println jade-data)
        {:status 200
         :body (jade/render jade-file jade-data)}))))

(defn static-handler
  [folder]
  (fn [{:keys [uri] :as req}]
    (let [path (str folder "/" uri)
          file (clojure.java.io/file path)]
      (when (.exists file)
        {:status 200
         :body file}))))

(defn make-handler
  [folder]
  (routes

   (haml-handler folder)

   (static-handler folder)

   (route/not-found "Not found")))

(def system
  (flow/flow
   :jetty ([handler port]
             (jetty/run-jetty handler {:port port :join? false}))
   :handler ([folder]
               (make-handler folder))))

(defn start
  [& [options]]
  (flow/run system options))

(defn stop
  [system]
  (.stop (:jetty system)))

(comment
  (def s (start {:folder "/tmp/foo" :port 9999}))
  (stop s))

(defn -main
  [& args]
  )
