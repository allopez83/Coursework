# Input
li $v0,4     # Prompt the user for unput
la $a0, prompt
syscall
li $v0, 5    # Read in an integer
syscall
move $s0,$v0 # Save n value to $s0

# Confirmation
li $v0, 4    # Print confirmation
la $a0, confirm
syscall
li $v0, 1    # Print out received n value
move $a0,$s0
syscall

# Manipulation test
move $s1, $s0      # Make a copy and put in $s1
addi $s1, $s1, 4   # Manipulate data by adding 4
li $v0, 4          # Print out result of factorial (Under construction)
la $a0, manipulate
syscall
li $v0, 1          # Print out manipulated data
move $a0, $s1
syscall

# Calculate the factorial
move $s1, $s0      # Reset value in $s1
mulo $s1, $s0, $s0 # Multiply by itself, placeholder for more complex stuff

# Print result
li $v0, 4      # Print out result of factorial (Under construction)
la $a0, result
syscall
li $v0, 1      # Print out manipulated data
move $a0, $s1
syscall

# Strings saved for outputs
.data
prompt:     .asciiz "Value for n: "
confirm:    .asciiz "I got an n value of: "
manipulate: .asciiz "\nn + 4 = "
result:     .asciiz "\nn! =  "



# Implement the recursive Factorial program from the text in Mars.
# Prompt the user for N and return N! If you find it helpful, you
# can use the Fibonacci example program from mars land for some
# scaffolding to help you get going.

# Observe carefully the operation of the stack to say how
# procedure calls (and in this case recursive procedure calls) work.