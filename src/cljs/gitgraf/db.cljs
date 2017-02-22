(ns gitgraf.db)

(def default-db
  {:name "gitgraf"
   :user {:profile {}
          :repos []}
   :loading? false
   :error false})
