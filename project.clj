(defproject nephrite "0.1.0-SNAPSHOT"
  :description "A tools for developing jade applications."
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [clj-jade "0.1.4"]
                 [ring "1.2.0"]
                 [compojure "1.1.5"]
                 [com.stuartsierra/flow "0.1.0"]
                 [cheshire "5.2.0"]
                 [org.clojure/tools.cli "0.2.4"]]
  :main nephrite.main)
