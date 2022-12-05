(ns app
  (:require [db])
  (:require [menu])
  (:gen-class)) 

(defn get-customer-name-input
  "Get the customer name from the user"
  []
  (println "Enter the customer name:")
  (read-line)
  )


(defn choice
  [data]
  (let [input (read-line)] (println)
  (case input
    "1" (menu/display-customer (get data :customer))
    "2" (menu/display-product data)
    "3" (menu/display-sales data)
    "4" (menu/display-customer-total-sales-amount-given-id (menu/get-customer-id-given-name (get-customer-name-input) (get data :customer)) data)
    "5" 5
    "6" 6
    :dunno)
    )
)

(defn choice-loop
  [data] 
  ((menu/display-menu)
  (cond (not (= (choice data) 6))
        (choice-loop data))
   (println "Good Bye!")
   (System/exit 0)
   )
  )

(defn -main 
  [] 
  (
    (choice-loop (db/load-db)))
  )

(comment 
  ""
  (-main)
  )

