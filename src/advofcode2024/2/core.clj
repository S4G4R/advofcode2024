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
  "Returns a seq of adjacent levels of the given report."
  [report]
  (partition 2 1 report))

(defn- increasing?
  "Given two adjacent levels of a report, returns `true` if the second
  level is greater than the first."
  [adjancency]
  (> (first adjancency) (second adjancency)))

(defn- decreasing?
  "Given two adjacent levels of a report, returns `true` if the second
  level is lesser than the first."
  [adjancency]
  (< (first adjancency) (second adjancency)))

(defn- valid-transition?
  "Given two adjacent levels of a report, returns `true` if the distance
  between them is between 1 (including) and 3 (including)."
  [adjancency]
  (<= 1 (abs (- (first adjancency) (second adjancency))) 3))

(defn- safe?
  "Returns `true` if the given report is safe. A report is said to be
  safe if all its levels are either increasing or decreasing, and the
  distance between consecutive levels is not less than 1 and not
  greater than 3."
  [report]
  (let [adjacencies (adjacent-levels report)]
    (and (or (every? increasing? adjacencies)
             (every? decreasing? adjacencies))
         (every? valid-transition? adjacencies))))

(defn safe-count
  "Returns the total count of safe reports."
  [input]
  (->> (parse input)
       (filter safe?)
       count))

(defn subreports
  "Returns a seq of subreports of the given report, whose length is one
  lesser than the report."
  [report]
  (->> (range 1 (inc (count report)))
       (map #(concat (take (dec %1) report) (nthrest report %1)))))

(defn- dampened-safe?
  "Like `safe?`, but also returns `true` if any subreport is also safe."
  [report]
  (or (safe? report)
      (some safe? (subreports report))))

(defn dampened-safe-count
  "Like `safe-count`, but also accounts for subreports."
  [input]
  (->> (parse input)
       (filter dampened-safe?)
       count))

(defn solve
  [input]
  (println "Safe count:" (safe-count input))
  (println "Dampened safe count:" (dampened-safe-count input)))
