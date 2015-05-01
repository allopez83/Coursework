#TODO: set up block mult - find t3, start of results

# Terms:
# m - matrix
# r - row

# $t5 - matrix to print, current position in a matrix (getMatrix and printMatrix)
# $t6 - counter fro getMatrix
# $t7 - 1st counter for inner loop (printMatrix, getMatrix)
# $t8 - 2nd counter for outer loop (printMatrix, getMatrix)
# $t9 - Data position in matrix
# $t0-t4 - used by matrix multiply

# $t6-t7 - block mult offsets, t6 being 'vertical' and t7 being 'horizontal'
# $t8 - 1st matrix to multiply (block mult)
# $t9 - 2nd matrix to multiply (block mult)

# $s0 - Reserved for matrix multiply
# $s1 - Reserved for matrix multiply
# $s2 - Reserved for matrix multiply

# $s4 - dimension of matrix (8)
# $s5 - start of m1
# $s6 - start of m2
# $s7 - start of m3 (results)

####################
# Start of program #
####################
# Save matrix information
li    $s4, 8        # Dimension of matrix
la    $s5, matrix   # Start of m1
la    $t5, matrix   # Position in matrix

# Create first matrix
jal   getMatrix
# Save address of m2
move  $s6, $t5

# Create second matrix
jal   getMatrix
# Save address m3, result matrix
move  $s7, $t5

###################
# Matrix multiply #
###################
# First quadrant
# Get offsets
li    $t6, 0   # Offset 0 starts on 'top' and 4 starts on 'bottom'
li    $t7, 0
# Find data address
sll   $t0, $t7, 2
add   $t9, $s6, $t0
mul   $t0, $t6, 8
sll   $t0, $t0, 2
add   $t8, $s5, $t0
# Block multiply
jal   blockMult
# First quadrant
# Get offsets
li    $t6, 0   # Offset 0 starts on 'top' and 4 starts on 'bottom'
li    $t7, 4
# Find data address
sll   $t0, $t7, 2
add   $t9, $s6, $t0
mul   $t0, $t6, 8
sll   $t0, $t0, 2
add   $t8, $s5, $t0
# Block multiply
jal   blockMult
# Third quadrant
# Get offsets
li    $t6, 4   # Offset 0 starts on 'top' and 4 starts on 'bottom'
li    $t7, 0
# Find data address
sll   $t0, $t7, 2
add   $t9, $s6, $t0
mul   $t0, $t6, 8
sll   $t0, $t0, 2
add   $t8, $s5, $t0
# Block multiply
jal   blockMult
# Fourth quadrant
# Get offsets
li    $t6, 4   # Offset 0 starts on 'top' and 4 starts on 'bottom'
li    $t7, 4
# Find data address
sll   $t0, $t7, 2
add   $t9, $s6, $t0
mul   $t0, $t6, 8
sll   $t0, $t0, 2
add   $t8, $s5, $t0
# Block multiply
jal   blockMult

# Print results
move  $t5, $s5      # Original matrix
jal   printMatrix
li    $v0, 4        # Results title
la    $a0, result
syscall
move  $t5, $s7      # Result matrix
jal   printMatrix

j     end

##############
# Procedures #
##############
##############
# matrixMult #
# non-block  #
##############
matrixMult:   # Matrix Multiplication Code
	# Reset temporary values
	sub   $t0, $t0, $t0
	sub   $t1, $t1, $t1
	sub   $t2, $t2, $t2
	sub   $t3, $t3, $t3
	sub   $t4, $t4, $t4
	sub   $t5, $t5, $t5
	sub   $t6, $t6, $t6
	sub   $t7, $t7, $t7
	sub   $t8, $t8, $t8
	sub   $t9, $t9, $t9
	# Load values required for matrixMult
	move  $t3, $s7         # $t3 will point to elements in result matrix
	move  $t4, $s4         # Matrix dimension
	li    $s0, 0           # 1st loop layer - Y axis of matrix 1 and result
L01:	li    $s1, 0           # 2nd loop layer - X axis of matrix 2 and result
L02:	li    $s2, 0           # 3rd loop layer - X axis of matrix 1 and Y axis of matrix 2 - Calculation offset
	# Data loading for matrix 2
L03:    mul   $t0, $s2, $s4    # Find the appropriate row by multiplying offset by matrix size
	addu  $t0, $t0, $s1    # Find appropriate column by adding offset
	sll   $t0, $t0, 2      # Turn into byte by multiplying 2^2, or 4
	addu  $t0, $t0, $s6    # Add base address of matrix 2
	lw    $t0, ($t0)       # Load value in matrix 2 from memory
	# Data loading for matrix 1
	mul   $t1, $s0, $s4    # Find the appropriate row by multiplying offset by matrix size
	addu  $t1, $t1, $s2    # Find appropriate column by adding offset
	sll   $t1, $t1, 2      # Turn into byte by multiplying 2^2, or 4
	addu  $t1, $t1, $s5    # Add base address of matrix 1
	lw    $t1, ($t1)       # Retrieve the value in matrix 1
	# Computational operations
	mul   $t0, $t0, $t1    # Multiply
	add   $t2, $t2, $t0    # Add result to matrix element
	sub   $t0, $t0, $t0    # Clean content of $t0 and $t1
	sub   $t1, $t1, $t1
	addiu $s2, $s2, 1      # Increment
	# Branch and store operations
	bne   $s2, $t4, L03
	sw    $t2, 0($t3)      # Store calculated data in $t2 into matrix 3, tracked by $t3
	addi  $t3, $t3, 4      # Increment counter $t3 by 4 to next matrix element
	sub   $t2, $t2, $t2    # Clean content of $t2
	addiu $s1, $s1, 1      # Increment and branch as necessary
	bne   $s1, $t4, L02
	addiu $s0, $s0, 1      # Increment and branch as necessary
	bne   $s0, $t4, L01
	
	jr    $ra              # Mission accomplished

##############
# matrixMult #
# block      #
##############
# Set m1 with t8, m2 with t9, offset with t7
blockMult:    # Block Matrix Multiplication Code
	# Reset temporary values
	sub   $t0, $t0, $t0
	sub   $t1, $t1, $t1
	sub   $t2, $t2, $t2
	sub   $t3, $t3, $t3
	sub   $t4, $t4, $t4
	# Load values required for matrixMult
	# Temporarily use t0 to find appropriate value for t3
	move  $t3, $s7         # $t3 will point to elements in result matrix
	mul   $t0, $t6, $s4    # Find row
	add   $t0, $t0, $t7    # Find column
	sll   $t0, $t0, 2      # Turn into byte
	add   $t3, $t3, $t0    # Combine with base address
	divu  $t4, $s4, 2      # Size parameter set to half of matrix dimension so blocking will work
	li    $s0, 0           # 1st loop layer - Y axis of matrix 1 with predefined offsets
L11:	li    $s1, 0           # 2nd loop layer - X axis of matrix 2 with predefined offsets
L12:	li    $s2, 0           # 3rd loop layer - X axis of matrix 1 and Y axis of matrix 2 - Calculation offset
	# Data loading for matrix 2
L13:    mul   $t0, $s2, $s4    # Find the appropriate row by multiplying offset by matrix size
	addu  $t0, $t0, $s1    # Find appropriate column by adding offset
	sll   $t0, $t0, 2      # Turn into byte by multiplying 2^2, or 4
	addu  $t0, $t0, $t9    # Add base address of matrix 2
	lw    $t0, ($t0)       # Load value in matrix 2 from memory
	# Data loading for matrix 1
	mul   $t1, $s0, $s4    # Find the appropriate row by multiplying offset by matrix size
	addu  $t1, $t1, $s2    # Find appropriate column by adding offset
	sll   $t1, $t1, 2      # Turn into byte by multiplying 2^2, or 4
	addu  $t1, $t1, $t8    # Add base address of matrix 1
	lw    $t1, ($t1)       # Retrieve the value in matrix 1
	# Computational operations
	mul   $t0, $t0, $t1    # Multiply
	add   $t2, $t2, $t0    # Add result to matrix element
	sub   $t0, $t0, $t0    # Clean content of $t0 and $t1
	sub   $t1, $t1, $t1
	addiu $s2, $s2, 1      # Increment
	# Branch and store operations
	bne   $s2, $s4, L13    # A 'wider array' is needed to calculate each element
	sw    $t2, 0($t3)      # Store calculated data in $t2 into matrix 3, tracked by $t3
	addi  $t3, $t3, 4      # Increment counter $t3 by 4 to next matrix element
	sub   $t2, $t2, $t2    # Clean content of $t2
	addiu $s1, $s1, 1      # Increment and branch as necessary
	bne   $s1, $t4, L12
	addiu $t3, $t3, 16     # Increment counter $t3 to start of next matrix row
	addiu $s0, $s0, 1      # Increment and branch as necessary
	bne   $s0, $t4, L11
	
	jr    $ra              # Mission accomplished

#############
# getMatrix #
#############
getMatrix:    # Automatically create a square matrix using memory location at $t5 incrementing at each row
	li    $t6, 1           # Give outer loop counter the matrix dimension
	move  $t8, $s4
getRowOuter:  # Create a row each pass
	move  $t7, $s4         # Give inner loop counter the matrix dimension
getRowInner:  # Create an element each pass
	# Store value into matrix element
	sw    $t6, ($t5)       # Store to element
	# Increment
	addi  $t5, $t5, 4      # Go to next memory location
	addi  $t7, $t7, -1     # Decrement counter for inner loop
	addi  $t6, $t6, 1      # Increment value counter
	# Looping instructions
	bgtz  $t7, getRowInner # Inner loop branch
	addi  $t8, $t8, -1     # Decrement counter for outer loop
	bgtz  $t8, getRowOuter # Outer loop branch
	jr    $ra              # Return to caller
	
###############
# printMatrix #
###############
printMatrix:  # Print out the matrix with starting point stored in $t5
	li    $v0, 4           # New line for formatting
	la    $a0, newLine
	syscall
	move  $t8, $s4         # Give outer loop counter the matrix dimension
printOuter:   # Print a row each pass
	move  $t7, $s4         # Give inner loop counter the matrix dimension
printInner:   # Print an element each pass
	# Action
	li    $v0, 1           # Print matrix element
	lw    $a0, 0($t5)
	syscall
	li    $v0, 4           # Divider for neatness
	la    $a0, divider
	syscall
	addi  $t5, $t5, 4      # Go to next memory location
	addi  $t7, $t7, -1     # Decrement counter for inner loop
	# Looping instructions
	bgtz  $t7, printInner  # Inner loop branch
	li    $v0, 4           # New line since this matrix row ends here
	la    $a0, newLine
	syscall
	addi  $t8, $t8, -1     # Decrement counter for outer loop
	bgtz  $t8, printOuter  # Outer loop branch
	jr    $ra              # Return to caller

	
j end               # Avoid junk code in case program falls through

##################
# End of program #
##################
end:

############################
# Variables and other data #
############################
.data

result:    .asciiz "\nMatrix multiplication result:\n"

divider:   .asciiz ",  "
newLine:   .asciiz "\n"

matrix:    .word   1 # Starting point of the matrices
