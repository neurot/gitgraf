(ns gitgraf.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub-raw
 :name
 (fn [db]
   (reaction (:name @db))))

(re-frame/reg-sub-raw
 :active-panel
 (fn [db _]
   (reaction (:active-panel @db))))

(re-frame/reg-sub-raw
 :github-id
 (fn [db]
   (reaction (get-in @db [:user :github-id]))))

(re-frame/reg-sub-raw
 :loading?
 (fn [db]
   (reaction (:loading? @db))))

(re-frame/reg-sub-raw
 :error?
 (fn [db]
   (reaction (:error @db))))


(re-frame/reg-sub-raw
 :user-profile
 (fn [db]
   (reaction (get-in @db [:user :profile]))))

(re-frame/reg-sub-raw
 :user-repos
 (fn [db]
   (reaction (get-in @db [:user :repos]))))





;;  (:require-macros [reagent.ratom :refer [reaction]])
;;     (:require [re-frame.core :as re-frame]))

;; (re-frame/reg-sub
;;  :name
;;  (fn [db]
;;    (:name db)))

;; (re-frame/reg-sub
;;  :active-panel
;;  (fn [db _]
;;    (:active-panel db)))

;; (re-frame/reg-sub
;;  :github-id
;;  (fn [db]
;;    (reaction (get-in @db [:user :github-id]))))

;; (re-frame/reg-sub
;;  :loading?
;;  (fn [db]
;;    (reaction (:loading? @db))))

;; (re-frame/reg-sub
;;  :error?
;;  (fn [db]
;;    (reaction (:error @db))))

;; (re-frame/reg-sub
;;  :user-profile
;;  (fn [db]
;;    (reaction (get-in @db [:user :profile]))))

;; (re-frame/register-sub
;;  :user-repos
;;  (fn [db]
;;    (reaction (get-in @db [:user :repos]))))
