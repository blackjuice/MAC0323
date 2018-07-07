# ***************************************************************
#
# Makefile genérico para compilar programas em Java
#
# ***************************************************************

JFLAGS = -g
JC = javac-introcs
JAVA = java-introcs

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $<

clean:
	$(RM) *.class *~

path:
	echo $(PATH)
