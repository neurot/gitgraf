(ns gitgraf.views
  (:require [re-frame.core :as re-frame]
            [reagent.core :as reagent]))

(defn loading-throbber
  []
  (let [loading? (re-frame/subscribe [:loading?])]
    [:div
     (if @loading?
       "..."
       "-")]))

(defn github-id-input
  []
  (let [loading? (re-frame/subscribe [:loading?])
        error? (re-frame/subscribe [:error?])
        github-id (reagent/atom "")
        on-click (fn [_]
                   (when-not (empty? @github-id)
                     (re-frame/dispatch [:set-github-id @github-id])
                     (reset! github-id "")))]
    (fn []
      [:div
       [:div
        [:input.search-box.border.p1 {:type "text"
                                      :placeholder "Enter Github ID"
                                      :on-change #(reset! github-id (-> % .-target .-value))}]
        [:button.btn {:type "button"
                      :on-click #(when-not @loading? (on-click %))}
         "Go"]]

       (when @error?
         [:p "¯\\_(ツ)_/¯  Bad github handle or rate limited!"])])))

(defn user-name-and-avatar
  []
  (fn []
    (let [user-profile (re-frame/subscribe [:user-profile])]
      [:div
       [:img.avatar {:src (get @user-profile "avatar_url")}]
       [:div.m1
        [:h1 (get @user-profile "name")]]])))

(defn user-repos-list
  []
  (let [user-repos (re-frame/subscribe [:user-repos])]
    (fn []
      [:div
       [:div.m1
        [:h3 "Hausdacher eigentlich gearbeitet"]
        [:p.ml1 "Stockwerke feierabend eigentlich geh federdecke aufzulosen gru sah hof. Tod macht see las namen neues. Gegenteil mitwisser ins uhr schwemmen. Tur ubel nein also ture sohn ihr. Stille erregt ku hubsch es morgen kummer andrer ja pa. Litze nadel zu ihrem blode in zu werde. Bildnis ten diesmal ehe hinuber drunten gut tal. "]]
       ;; [:ul (map-indexed (fn [i repo]
       ;;                     (vector :li.list-reset {:key i}
       ;;                             [:h3 (get repo "name")]
       ;;                             [:p (get repo "description")]))
       ;;                   @user-repos)]
       (map-indexed (fn [i repo]
                            (vector :div.ml1 {:key i}
                                    [:h3 (get repo "name")]
                                    [:p.ml1 (get repo "description")]))
                          @user-repos)])))
;; home
(defn home-panel []
  (let []
    (fn []
      [:div])))


;; profile

(defn profile-panel []
  (fn []
    [:div "This is the Profile Page."
     [:div [:a {:href "#/"} "go to Home Page"]]]))



;; main

(defmulti panels identity)
(defmethod panels :home-panel [] [home-panel])
(defmethod panels :profile-panel [] [profile-panel])
(defmethod panels :default [] [:div])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      [:div
       [loading-throbber]
       [github-id-input]
       [user-name-and-avatar]
       [user-repos-list]])))
       ;; (panels @active-panel)












;; (defn user-name-and-avatar
;;   []
;;   (fn []
;;     (let [user-profile (re-frame/subscribe [:user-profile])]
;;       [:div
;;        [:img {:src (get @user-profile "avatar-url")}]
;;        [:h5 (get @user-profile "name")]])))

;; (defn github-id-input []
;;   (let [github-id (reagent/atom "")
;;         on-click (fn [_]
;;                    (when-not (empty? @github-id)
;;                      (re-frame/dispatch [:set-github-id @github-id])
;;                      (reset! github-id "")))]
;;     (fn []
;;       [:div
;;        [:input {:type "text"
;;                 :placeholder "Enter GitHub ID"
;;                 :on-change #(reset! github-id (-> % .-target .-value))}]
;;        [:button.btn.btn-primary {:type "button"
;;                                  :on-click #(on-click %)}
;;         "Search"]
;;        ])))

;; ;; home

;; (defn home-panel []
;;   (let [name (re-frame/subscribe [:name])
;;         profile-picture (re-frame/subscribe [:profile-picture])]
;;     (fn []
;;       [:div
;;        [:h4 (str "Hi from " @name ".")]
;;        [github-id-input]
;;        [:div.m1 [:a.btn.btn-primary {:href "#/about"} "goto About Page"]]
;;        [user-name-and-avatar]
;;        ])))


;; ;; about

;; (defn about-panel []
;;   (fn []
;;     [:div
;;      [:h2 "About GitGraf"]
;;      [:h4 "This is the About Page."]
;;      [:p "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet."]
;;      [:div.m1 [:a.btn.btn-primary {:href "#/"} "goto Home Page"]]]))


;; ;; main

;; (defn- panels [panel-name]
;;   (case panel-name
;;     :home-panel [home-panel]
;;     :about-panel [about-panel]
;;     [:div]))

;; (defn show-panel [panel-name]
;;   [panels panel-name])

;; (defn main-panel []
;;   (let [active-panel (re-frame/subscribe [:active-panel])]
;;     (fn []
;;       [show-panel @active-panel])))
