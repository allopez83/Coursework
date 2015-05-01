mm:
	li    $t1, 3
	li    $s0, 0

L1:     li    $s1, 0

L2:     li    $s2, 0

L3:     sll   $t0, $s2, 5
	addu  $t0, $t0, $s1
	sll   $t0, $t0, 3
	addu  $t0, $a2, $t0
	l.d   $f16, 0($t0)
	sll   $t0, $s5, 5
	addu  $t0, $t0, $s2
	sll   $t0, $t0, 3
	addu  $t0, $a1, $t0
	l.d   $f18, 0($t0)
	addiu $s1, $s1, 1
	bne   $s1, $t1, L2
	addiu $s0, $s0, 1
	bne   $s0, $t1, L1