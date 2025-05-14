(ns advofcode2024.2.core
  "https://adventofcode.com/2024/day/2"
  (:require [clojure.string :as string]))

(defn- parse-one
  [report]
  (->> (re-seq #"\d+" report)
       (map parse-long)))

(defn- parse
  [input]
  (->> (string/split-lines input)
       (map string/trim)
       (map parse-one)))

(defn- adjacent-levels
  [levels]
  (partition 2 1 levels))

(defn- increasing?
  [adjancency]
  (> (first adjancency) (second adjancency)))

(defn- decreasing?
  [adjancency]
  (< (first adjancency) (second adjancency)))

(defn- valid-transition?
  [adjancency]
  (<= 1 (abs (- (first adjancency) (second adjancency))) 3))

(defn- safe?
  [report]
  (let [adjacencies (adjacent-levels report)]
    (and (or (every? increasing? adjacencies)
             (every? decreasing? adjacencies))
         (every? valid-transition? adjacencies))))

(defn safe-count
  [input]
  (->> (parse input)
       (filter safe?)
       count))

(defn- dampened-safe?
  [report]
  (let [possible-reports (map #(concat (take (dec %1) report)
                                       (nthrest report %1))
                              (range 1 (inc (count report))))]
    (or (safe? report)
        (some safe? possible-reports))))

(defn dampened-safe-count
  [input]
  (->> (parse input)
       (filter dampened-safe?)
       count))

(defn solve
  [input]
  (println "Safe count:" (safe-count input))
  (println "Dampened safe count:" (dampened-safe-count input)))
