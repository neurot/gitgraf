(ns gitgraf.views
    (:require [re-frame.core :as re-frame]))


;; home

(defn home-panel []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [:div
       [:div.m1 [:a.btn.btn-primary {:href "#/about"} "go to About Page"]]
       [:div (str "Hi from " @name ". This is the Home Page.")]])))


;; about

(defn about-panel []
  (fn []
    [:div
     [:div.m1 [:a.btn.btn-primary {:href "#/"} "go to Home Page"]]
     [:div "This is the About Page."]]))


;; main

(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :about-panel [about-panel]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      [show-panel @active-panel])))
