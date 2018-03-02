
# Author: Drew Daniel
# In Class Project 3
# Replace all lowercase letters into UPPERCASE LETTERS in a given string
# Also count how many characters are in this string

# Output format must be shown below

.data

prompt: .asciiz "Given String is = "
str: .asciiz "abaaabaaabbbcccdddaaddbaabbbababababcd"
ans: .asciiz "\n\nReplaced string is = "
count: .asciiz"\n\n\nNumber of letters in this string is = "

.text
.globl main
main:

# $t0 is base location of string str
# $t1 holds the count of letters
# $t2 holds individual characters



la $a0, prompt		#print prompt
li $v0, 4
syscall

la $a0, str		#print prompt
li $v0, 4
syscall

la $t0, str		#Place string into register

li $t1, 0 		#starts counter at 0


la $a0, ans
li $v0, 4
syscall


Loop:			# Loop to turn lower case to upper case


lb $t2, 0($t0)
beqz $t2, end
addi $t3, $t2, -32
sb $t3, 0($t0)

addi $t1, $t1, 1
addi $t0, $t0, 1

j Loop

end:



la $a0, str
li $v0, 4
syscall

la $a0, count
li $v0, 4
syscall

move $a0, $t1
li $v0, 1
syscall

li $v0, 10
syscall