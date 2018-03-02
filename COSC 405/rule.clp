(defrule R1
	?begin <- (intial-fact)
=>
	(printout t "Hello World" crlf)
	(retract ?begin)
)

