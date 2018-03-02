;;;======================================================
;;;  Knight's tour on 3x3 board
;;;
;;;  Author: Terry Fries
;;;
;;;  To execute: load, reset and run.
;;;======================================================

;;;(deffacts f1
 ;;; (square 1)
 ;;; (goal 2))
  
(defrule start-up
	?i <- (initial-fact)
=>
	(printout t "Start Square: ")
	(bind ?response (read))
	(assert (square ?response))
	(printout t "Goal Square: ")
	(bind ?response (read))
	(assert (goal ?response))
	(retract ?i))

(defrule square1a
  ?s <- (square 1)
=>
  (assert (square 8))
  (retract ?s)
  (printout t "Move to square 8" crlf))

(defrule square1b
  ?s <- (square 1)
=>
  (assert (square 6))
  (retract ?s)
  (printout t "Move to square 6" crlf))

(defrule square2a
  ?s <- (square 2)
=>
  (assert (square 9))
  (retract ?s)
  (printout t "Move to square 9" crlf))

(defrule square2b
  ?s <- (square 2)
=>
  (assert (square 7))
  (retract ?s)
  (printout t "Move to square 7" crlf))

(defrule square3a
  ?s <- (square 3)
=>
  (assert (square 4))
  (retract ?s)
  (printout t "Move to square 4" crlf))

(defrule square3b
  ?s <- (square 3)
=>
  (assert (square 8))
  (retract ?s)
  (printout t "Move to square 8" crlf))

(defrule square4a
  ?s <- (square 4)
=>
  (assert (square 9))
  (retract ?s)
  (printout t "Move to square 9" crlf))

(defrule square4b
  ?s <- (square 4)
=>
  (assert (square 3))
  (retract ?s)
  (printout t "Move to square 3" crlf))

(defrule square6a
  ?s <- (square 6)
=>
  (assert (square 1))
  (retract ?s)
  (printout t "Move to square 1" crlf))

(defrule square6b
  ?s <- (square 6)
=>
  (assert (square 7))
  (retract ?s)
  (printout t "Move to square 7" crlf))

(defrule square7a
  ?s <- (square 7)
=>
  (assert (square 2))
  (retract ?s)
  (printout t "Move to square 2" crlf))

(defrule square7b
  ?s <- (square 7)
=>
  (assert (square 6))
  (retract ?s)
  (printout t "Move to square 6" crlf))

(defrule square8a
  ?s <- (square 8)
=>
  (assert (square 3))
  (retract ?s)
  (printout t "Move to square 3" crlf))

(defrule square8b
  ?s <- (square 8)
=>
  (assert (square 1))
  (retract ?s)
  (printout t "Move to square 1" crlf))

(defrule square9a
  ?s <- (square 9)
=>
  (assert (square 2))
  (retract ?s)
  (printout t "Move to square 2" crlf))

(defrule square9b
  ?s <- (square 9)
=>
  (assert (square 4))
  (retract ?s)
  (printout t "Move to square 4" crlf))

(defrule goal-found
  (declare (salience 100))
  ?z <- (square ?x)
  (goal ?y)
  (test (= ?x ?y))
=>
  (printout t "Goal found" crlf)
  (retract ?z))
  

	
	
	