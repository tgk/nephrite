(ns nephrite.main
  (:require [nephrite.system :as system]
            [clojure.tools.cli :refer [cli]])
  (:gen-class))

(defn -main
  [& args]
  (let [[options _ banner]
        (cli args
             ["-p" "--port" "Expose content on this port."
              :parse-fn #(Integer. %) :default 8000]

              ["-f" "--folder" "Read content, haml and data from this base folder."
               :default "."]

              ["-h" "--help" "Prints help."
               :flag true])]
    (when (:help options)
      (println banner)
      (System/exit 0))
    (system/start options)))
