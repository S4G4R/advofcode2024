(ns advofcode2024.1.core-test
  (:require
   [advofcode2024.1.core :as core]
   [clojure.java.io :as io]
   [clojure.test :refer :all]))


(def sample-input
  "3   4
   4   3
   2   5
   1   3
   3   9
   3   3")

(deftest total-distance-test
  (testing "Sample input"
    (is (= 1      (core/total-distance sample-input))))
  (testing "Actual input"
    (is (= 1189304 (core/total-distance (slurp (io/resource "1/input.txt")))))))

(deftest similarity-score-test
  (testing "Sample input"
    (is (= 31       (core/similarity-score sample-input))))
  (testing "Actual input"
    (is (= 24349736 (core/similarity-score (slurp (io/resource "1/input.txt")))))))
