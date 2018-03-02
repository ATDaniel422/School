;;; Getting Familiar with CLIPS
;;; Drew Daniel
;;; COSC 405

(deffacts relation
	(father bill george)
	(father amy george)
	(father mary bill)
	(father sam bill)
)

(defrule start
	?i <- (initial-fact)
=>
	(printout t "Enter name of father: ")
	(bind ?father (read))
	(retract ?i)
	(assert (dad ?father))
	(assert (step find-children))
)

(defrule child	
	(step find-children)
	(dad ?father)
	(father ?child ?father)
=>
	(printout t ?child " is a child of " ?father crlf)
)


