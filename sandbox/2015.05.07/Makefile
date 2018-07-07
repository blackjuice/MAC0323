# ***************************************************************
#
# Makefile genérico para compilar programas em Java (Algs4th)
#
# ***************************************************************

JFLAGS = -g
JC = javac-algs4
JAVA = java-algs4

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $<

clean:
	$(RM) *.class *~
