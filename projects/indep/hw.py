
f = 0         #Number of Generations calculated
p = 0         #Dominant Allele Frequency
q = 0         #Recessive Allele Frequency
dHom = 0      #% of population dominant homozygous
dHomSR = 0    #dominant homozygous survival rate
dHomS = 0     #% survived after selection
het = 0       #% of population heterozygous
hetSR = 0     #heterozygous survival rate
hetS = 0      #% survived after selection
rHom = 0      #% of population recessive homozygous  
rHomSR = 0    #recessive homozygous survival rate
rHomS = 0     #% survived after selection
cont = ""
calc = 1      #Current calculation #
print_all = "n"
no_select = "n"
bar = "\n====================================================================== \n"
perr = "\n #####################  Input Percentage Error!  ########################### \n"

def printout (p,q,dHom,het,rHom):
        print(" Dominant Allele Frequency =  {0}".format (round(p,10)))
        print(" Recessive Allele Frequency = {0}".format (round(q,10)))
        print(" % Homozygous Dominant =      {0}%".format (round(dHom*100,10)))
        print(" % Heterozygous =             {0}%".format (round(het*100,10)))
        print(" % Homozygous Recessive =     {0}%".format (round(rHom*100,10)))
    


#  ============================Input Data:================================  #


print("Solver Initiated:")

while True:
    print("Calculation {0}\n".format(calc))
    
    dHom = float(input (" Homozygous Dominant: "))       
    dHomSR = float(input("     % survival: "))      
    het = float(input(" \n Heterozygous: "))
    hetSR = float(input("     % survival: "))   
    rHom = float(input(" \n Homozygous Recessive: "))
        
    rHomSR = float(input("     % survival: "))

    nrm = dHom + het + rHom
    dHom /= nrm
    het /= nrm
    rHom /= nrm

    f = int(input("\n # Generations: "))
##    if f > 100:
##        print " That's many generations!"
##        cont = raw_input ("\nContinue? (y/n): ")
##        if cont != "y":
##                break    
        
    print_all = input(" \n print all generation frequencies? (y/n): ")
    if print_all =="Y":
        print_all = "y"
    if print_all != "y":
        print_all = "n"
        if f > 100 and print_all == "y":
            print(" That's a lot to print!")
            cont = raw_input ("\n Continue? (y/n): ")
            if cont != "y":
                    break

##    no_select = raw_input("print generation frequencies before selection occurrs? (y/n): ")
##    if print_all != "y":
##        print_all = "n"
    
    print(bar)
            
#  =============================Calculations:==============================  #

#   Parent Generation

    #Calculate allele frequencies
    if print_all == "y": 
        q = ((rHom) ** (.5))
        p = 1-q

        print("\n Parent Generation:\n")
        printout (p,q,dHom,het,rHom)   


#   First Generation

    #Calculate number of animals survived
    dHomS = (dHom * dHomSR)
    hetS = (het * hetSR)
    rHomS = (rHom * rHomSR)

    #Reset frequencies to = 100
    dHom = dHomS * (1/(dHomS+hetS+rHomS))
    het = hetS * (1/(dHomS+hetS+rHomS))
    rHom = rHomS * (1/(dHomS+hetS+rHomS))

    #Calculate allele frequencies
    p = (2*dHom + het)/(2*(dHom+het+rHom))
    q = 1 - p

    if print_all == "y" or f == 1: 
        print("\n\n Generation 1:\n")
        printout (p,q,dHom,het,rHom)
        
#  ======================Multi-Generation Calculator:=======================  #

#   Loops until current generation is equal to total generations
    generation_current = 2    
    while generation_current <= f:
        
        #redistribute HW frequencies
        dHom = p*p
        het = 2*p*q
        rHom = q*q

        #number survived
        dHomS = (dHom * dHomSR)
        hetS = (het * hetSR)
        rHomS = (rHom * rHomSR)

        # normalizes to 100
        dHom = dHomS * (1/(dHomS+hetS+rHomS))
        het = hetS * (1/(dHomS+hetS+rHomS))
        rHom = rHomS * (1/(dHomS+hetS+rHomS))

        #Calculate allele frequencies
        p = (2*dHom + het)/(2*(dHom+het+rHom))
        q = 1 - p
                
        if generation_current < f and print_all == "y" : 
            print("\n\n Generation {0}:\n".format(generation_current))
            printout (p,q,dHom,het,rHom)

        elif generation_current == f:
            print("\n\n Generation {0}:\n".format(f))
            printout (p,q,dHom,het,rHom)
            print(bar)
            calc = calc + 1 #sets the current calculation # up by one after entire calculation id done

        generation_current += 1
