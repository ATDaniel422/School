
;; Author: Drew Daniel
;; COSC 405-001
;; Bagger

;; Deftemplates*******************

(deftemplate item
	(slot name)
	(slot bottle)
	(slot size)
	(slot crushable)
)

(deftemplate bag
	(slot number)
	(slot weight)
	(slot bottle)
)

(deftemplate item-in-bag-large-items
	(slot bag-number)
	(slot item-name)
)


;; *****************************************
;; *    Rule to load order facts     *******
;; *****************************************

(defrule start
	?i <- (initial-fact)
=>
	(printout t "Select order (1 or 2): ")
	(bind ?order (read))
	(if (eq ?order 1)
		then (load-facts "order1.txt")
		else (load-facts "order2.txt")
	)
	(printout t crlf)
	(retract ?i)
	(assert (pepsi no))					;; flag to indicate pepsi check not done
	(assert (step check-order))			;; start check order step
	(assert (last-bag-num 0))			;; first bag not ready
)

;; **********************************
;; *   Rules to check order         *
;; **********************************

(defrule B0a							;; if pepsi, set flag so we do not ask
	(step check-order)
	(item (name pepsi))
	?pep <- (pepsi no)
=>
	(retract ?pep)
	(assert (pepsi yes))
)

(defrule B0b							;; if chips and no pepsi, ask customer
	(step check-order)
	(item (name potato-chips))
	?pep <- (pepsi no)
=>
	(printout t "Do you want a Pepsi to wash down those chips? (y/n): ")
	(bind ?answer (read))
	(if (eq ?answer y)
		then
			(printout t "Add a Pepsi to the order" crlf)
			(assert (item (name pepsi) (bottle yes) (size 4) (crushable no)))
	)
	(retract ?pep)
	(assert (pepsi yes))
)

(defrule B1								;; check-order done
	?step <- (step check-order)
=>
	(printout t crlf "Begin bagging the large items" crlf)
	(retract ?step)
	(assert (step bag-large-items))
	(assert (bag (number 1) (weight 0) (bottle no)))
)

;;**********************************
;;* Rules for bagging large items  *
;;**********************************

(defrule B2								;; bag bottle first
	(step bag-large-items)
	?bottle <- (item (name ?n) (bottle yes) (size ?s))
	?b <- (bag (number ?bn) (weight ?bw) (bottle no))
	(test (<= ?bw 8))
=>
	(printout t "Place " ?n " in bag # " ?bn crlf)
	(retract ?b)
	(assert (bag (number ?bn) (weight (+ ?bw 4)) (bottle yes)))
	(retract ?bottle)
)

(defrule B3								;; bag a large item other than bottle
	(step bag-large-items)
	?lrg-item <- (item (name ?n) (bottle no) (size 4) (crushable no) )
	?b <- (bag (number ?bn) (weight ?bw) (bottle ?bot))
	(test (<= ?bw 8))
=>
	(printout t "Place " ?n " in bag # " ?bn crlf)
	(retract ?b)
	(assert (bag (number ?bn) (weight (+ ?bw 4)) (bottle ?bot)))
	(retract ?lrg-item)
)

(defrule B4								;; starting a fresh bag
	(step bag-large-items)
	?lrg-item <- (item (name ?n) (size 4) (crushable no))
	?b <- (bag (number ?bn) (weight ?bw) (bottle ?bot))
=>
	(assert (bag (number (+ ?bn 1)) (weight 0) (bottle no)))
)

(defrule B5	
	?step <- (step bag-large-items)
=>
	(retract ?step)
	(assert (step bag-medium-items))
)

;;**********************************
;;* Rules for bagging medium items *
;;**********************************

(defrule B6								;; bag a medium item 
	(step bag-medium-items)
	?med-item <- (item (name ?n) (size 2) (crushable no))
	?b <- (bag (number ?bn) (weight ?bw) (bottle ?bot))
	(test (<= ?bw 10))
=>
	(printout t "Place " ?n " in bag # " ?bn crlf)
	(retract ?b)
	(assert (bag (number ?bn) (weight (+ ?bw 2)) (bottle ?bot)))
	(retract ?med-item)
)

(defrule B7								;; starting a fresh bag
	(step bag-medium-items)
	?med-item <- (item (name ?n) (size 2) (crushable no))
	?b <- (bag (number ?bn) (weight ?bw) (bottle ?bot))
=>
	(assert (bag (number (+ ?bn 1)) (weight 0) (bottle no)))
)

(defrule B8	
	?step <- (step bag-medium-items)
=>
	(retract ?step)
	(assert (step bag-small-items))
)

;;**********************************
;;* Rules for bagging small items  *
;;**********************************

(defrule B9								;; bag a small item 
	(step bag-small-items)
	?sml-item <- (item (name ?n) (size 1) (crushable no))
	?b <- (bag (number ?bn) (weight ?bw) (bottle ?bot))
	(test (<= ?bw 11))
=>
	(printout t "Place " ?n " in bag # " ?bn crlf)
	(retract ?b)
	(assert (bag (number ?bn) (weight (+ ?bw 1)) (bottle ?bot)))
	(retract ?sml-item)
)

(defrule B10								;; starting a fresh bag
	(step bag-small-items)
	?sml-item <- (item (name ?n) (size 1) (crushable no))
	?b <- (bag (number ?bn) (weight ?bw) (bottle ?bot))
=>
	(assert (bag (number (+ ?bn 1)) (weight 0) (bottle no)))
)

(defrule B11
	?step <- (step bag-small-items) 
=>
	(retract ?step)
	(assert (step bag-crushable))
)

;;********************************
;;* Rules to bag crushable items *
;;********************************

(defrule B12									;; Bag a large crushable item
	(step bag-crushable)
	?csh-item <- (item (name ?n) (size 4) (crushable yes))
	?b <- (bag (number ?bn) (weight ?bw) (bottle ?bot))
	(test (<= ?bw 8))
=>
	(printout t "Place " ?n " in bag # " ?bn crlf)
	(retract ?b)
	(assert (bag (number ?bn) (weight (+ ?bw 4)) (bottle ?bot)))
	(retract ?csh-item)
)

(defrule B13									;; Bag a medium crushable item
	(step bag-crushable)
	?csh-item <- (item (name ?n) (size 2) (crushable yes))
	?b <- (bag (number ?bn) (weight ?bw) (bottle ?bot))
	(test (<= ?bw 10))
=>
	(printout t "Place " ?n " in bag # " ?bn crlf)
	(retract ?b)
	(assert (bag (number ?bn) (weight (+ ?bw 2)) (bottle ?bot)))
	(retract ?csh-item)
)

(defrule B14									;; Bag a small crushable item
	(step bag-crushable)
	?csh-item <- (item (name ?n) (size 1) (crushable yes))
	?b <- (bag (number ?bn) (weight ?bw) (bottle ?bot))
	(test (<= ?bw 11))
=>
	(printout t "Place " ?n " in bag # " ?bn crlf)
	(retract ?b)
	(assert (bag (number ?bn) (weight (+ ?bw 1)) (bottle ?bot)))
	(retract ?csh-item)
)

(defrule B15
	?step <- (step bag-crushable)
=>
	(retract ?step)
)


