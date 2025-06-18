(ns advofcode2024
  (:require [clojure.java.io :as io]))


(defn -main
  [& [day]]
  (println "Solving day" day)
  (let [day-ns (symbol (str "advofcode2024." day ".core"))
        _ (require day-ns)
        {doc :doc} (meta (the-ns day-ns))
        day-var (resolve (symbol (str day-ns) "solve"))]
    (println doc)
    (with-open [reader (io/reader (io/resource (str day "/input.txt")))]
      (day-var reader))))
