#Write a MIPS code print out the length of the given string

.data

str: .asciiz"Testing My Program"


prompt: .asciiz "Given String is =   "
ans: .asciiz "\n\n Length of the String is(including spaces) = "
end: .asciiz "\n\n YOU HAVE A NICE WEEKEND AND I WILL SEE YOU ON TUESDAY \n"



#t0 holds each byte from the string in turn
#t1 contains the count of the characters
#t2 points the string


.text
.globl main
main:


la $a0, prompt
li $v0, 4
syscall

la $a0, str
li $v0, 4
syscall

la $a0, ans
li $v0, 4
syscall

la $t2, str		#points to the string
li $t1, 0		#holds the count


Loop:

lb $t0, 0($t2) 		#get a byte from string
beqz $t0, StrEnd	#zero means end of the sting
addi $t1, $t1, 1	#increment the count
addi $t2, $t2, 1	#move the poin one character

j Loop


StrEnd:

move $a0, $t1
li $v0, 1
syscall

la $a0, end
li $v0, 4
syscall

li $v0, 10
syscall