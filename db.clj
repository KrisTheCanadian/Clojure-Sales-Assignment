(ns db
  (:require [clojure.string :as str]))

(defn load-cust-db
  "Load the Customer Database"
  []
  (str/split (slurp "./cust.txt") #"\n")
  )

(defn load-prod-db
  "Load the Product Database"
  []
  (str/split (slurp "./prod.txt") #"\n"))

(defn load-sales-db
  "Load the Sales Database"
  []
  (str/split (slurp "./sales.txt") #"\n"))

(defn load-db 
  "Load the Database"
  []
  (hash-map :customer (load-cust-db), :product (load-prod-db), :sale (load-sales-db))
)


(comment
  "cust.txt -> <custID, name, address, phoneNumber>"
  "prod.txt -> <prodID, itemDescription, unitCost>"
  "sales.txt -> <salesID, custID, prodID, itemCount>" 
  )