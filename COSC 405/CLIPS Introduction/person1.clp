(deftemplate person
	(slot age)
	(slot name)
	(slot gender)
)

(deffacts people
	(person (age 20) (name "Mike") (gender m))
	(person (age 40) (name "James") (gender m))
	(person (age 24) (name "Mark") (gender m))
	(person (age 30) (name "Amy") (gender f))
	(person (age 42) (name "Susan") (gender f))
	(person (age 28) (name "John") (gender m))
)

(defrule title
	(declare (salience 100))
	?i <- (initial-fact)
=>
	(printout t "Enter Minimum Age: " crlf)
	(bind ?minimum (read))
	(retract ?i)
	(assert (min ?minimum))
	(printout t "Persons " ?minimum " and over" crlf)
)

(defrule age-21
	(person (name ?n) (age ?a))
	(min ?minimum)
	(test (>= ?a ?minimum))
=>
	(printout t ?n crlf)
)