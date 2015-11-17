#nt2mdl -v MDLHELP -v NMTRAN control_stream_file
# Pragmas to aid conversion
# ; parameter_name
# ; units="my_units" for data items and parameter variables
# ;@MCL_ETAPPV marks start of INDIVIDUAL PREDICTION block e.g. to separate from GROUP_VARIABLES
# ;@MCL_$ERROR  marks MODEL_PREDICTION block after LIBRARY, COMPARTMENT, DEQ e.g. when NM-TRAN uses $PRED. Equivalent to start of $ERROR.
# ;@MCL_start_IGNORE and @MCL_end_IGNORE used to skip NM-TRAN code conversion
# ;@MCL_start_COVARIATES and @MCL_end_COVARIATESused to put code in model object COVARIATES block
# ;@MCL_" statements are used as MCL verbatim code
# ;LIBLIST  specifies a library call (not currently supported by MDL-IDE)
BEGIN {
# Previous changes moved to end of file 27 Sep 2014, 09 Sep 2015
# 14 Nov 2015 version 2.033 beta
# MCL 7 Product 4.1 "new grammmar"
# data object source has name
# use is ... 
# type is ...
# no units or type=categorical, type=continuous
# when otherwise -> if then else
# no MODEL_OUTPUT_VARIABLES
# no covariate use for special uses, target attribute to point to DEQ compartment, anonymous transfer compartment
# depot attribute for input to DEQ compartment
# ignore character ignored

   version="2.033 MCL 7"
   print "# nt2mdl "version" Nick Holford n.holford@auckland.ac.nz"


# Command line arguments
   ishelp=((index(toupper(MDLHELP),"Y")==1)) # Default is not to print MDL help as comments
   isnmtran=((index(toupper(NMTRAN),"Y")==1)) # Default is not to print extracts from NM-TRAN input as comments
   istablecode=((index(toupper(TABLECODE),"Y")==1)) # Default is print MDL_OUTPUT block and do not print $TABLE as TARGET_CODE

# Internal arguments
   iscompartment=1 # Translate ADVAN and TRAN to COMPARTMENT instead of LIBRARY sub-block

   if (!iscompartment) {
      iscmtido=1 # Translate ADVAN and TRAN to CMT_IDO instead of LIBRARY sub-block
   }

   PIDGIN="Y"
   ispidgin=((index(toupper(PIDGIN),"Y")==1)) # Simplified MDL for IOG  http://en.wikipedia.org/wiki/Pidgin
   # Initialize
   if (ispidgin) {
      usedataname=1 
      usecovmatrix=0 
      usetelobj=0
      useifelse=0
      usefulltask=0   # 0.5 #MCL 4.1 new grammar partial support for task and target code
      usetargetcode=1
      useerrorexit=0
      usemcl5obj=0
      useonervd=0
      usedataidv=0
      usetaskmodel=0
      usemdlout=0
      usevarlist=0
      useunits=0
      usedvidcat=0   #MCL 4.1 new grammar does not support categories with dvid
      useallcov=0 # use is covariate not allowed for amt, dv, evid
      useelimname=0 # use named list for elimination compartment else use anonymous list
      usetransfername=0 # use named list for transfer compartment else use anonymous list
      usedeqto=0 # use to= to name DEQ compartment else use target=
      usedeqdirect=0 # use direct for compartment type or depot if false
      useignore=0 # use ignore character to drop data rows
   } else {
      usedataname=1
      usecovmatrix=1 
      usetelobj=1
      useifelse=1
      usefulltask=1
      usetargetcode=1
      useerrorexit=1
      usemcl5obj=1  # use MCL 5 model and data object structure
      useonervd=1 # Single random variable definition block
      usedataidv=0
      usetaskmodel=1
      usemdlout=1
      usevarlist=1
      useunits=1
      usedvidcat=1
      useallcov=1 # use is covariate allowed for all data variables
      useelimname=1 # use named list for elimination compartment else use anonymous list
      usetransfername=1 # use named list for transfer compartment else use anonymous list
      usedeqto=1 # use to= to name DEQ compartment else use target=
      usedeqdirect=1 # use direct for compartment type or depot if false
      useignore=1 # use ignore character to drop data rows
   }
      ismcl5list=0
      if (ismcl5list) {
         liststart="("; listend=")"; listname="list"; listconnect="="
      } else {
         liststart="{"; listend="}"; listname=""; listconnect=": " ; listdistconnect=" ~ "; odeconnect=listconnect
      }

# Product 4
   iscatdefined=1 # define categories with names and values
   iscmtlink=1 # compartment link attributes defined for use=cmt  otherwise Product 3 administration attribute with use=amt
   iscmtlink2cmt=0 # link to amt compartment when use=cmt otherwise link to amt compartment when use=amt
   isleveltype=1 # when isleveltype=1 add type attribute with level attribute (for design object)

#  NM-TRAN to MCL translation direction
   REVERSE=1

# initialize variables
   istheta=0 ;isomega=0; issigma=0; isother=0; wasother=0
   ntheta=0; nomega=0; nsigma=0; nrec=0; nomblk=0; ismclcov=0
   isinput=0;isdata=0;ispred=0;isdes=0;isblock=0;  inputline="";isgroup=1
   ispro=0; haserror=0; ismdlpred=0
   npredline=0; nindline=0; nodeline=0; naesline=0; nerrline=0; ndatab=0
   nestline=0; ncovline=0; nsimline=0; ntabline=0; nsubline=0; isverbatim=0
   nsubline=0; nmixline=0; nmodline=0; npriline=0; ntransline=0; ncmt=0; ndadt=0; nobsblock=0
   ninputline=0 # not currently in use (17 May 2014)
   nazero=0; ifcount3=0; neq3line=0 ; ifcount4=0; neq4line=0; nabbline=0; nproline=0
   neditline=0; ignorechar=""; nranline=0; ndeqline=0; nliblist=0; ntargetcode=0; ncorrlist=0
   condition=""; likelihood=""; reserror=""; hasamount=0; covmatnum=0
   estrecord=""; covrecord=""; advan_num=0; tran_num=0; tol_num=0; liketype=""; estrectype=""; estblocktype=""
   istask_estimate=0; istask_simulate=0; MLX_omega_type=""
   hasamt=0; hasrate=0; hascmt=0; hasadm=0; hasmdv=0; hasevid=0; hasdvid=0; hasocc=0; ndvid=0; wasflag=0
   ismcl=0; ismclignore=0;ismclverbatim=0; CMTNAME=""; DVIDNAME="";ncatdef=0
   zerocmt()

# Logical operators
   nlogic=split("== <= >= !=",LOGIC)

   #    $DATA
   #    [filename|*] [(format)] [IGNORE=c1] [NULL=c2]
   #    [IGNORE=(list)...|ACCEPT=(list)...]
   #    [NOWIDE|WIDE] [CHECKOUT]
   #    [RECORDS=n1|RECORDS=label]
   #    [LRECL=n2] [NOREWIND|REWIND]
   #    [NOOPEN] [LAST20=n3] [TRANSLATE=(list)]
   #    [BLANKOK]
   ndatopt=split("NOWIDE WIDE CHECKOUT NOREWIND REWIND NOOPEN BLANKOK",DATOPT)
   #   $TABLE
   #   [PRINT|NOPRINT] [FILE=filename]
   #   [NOHEADER|ONEHEADER]
   #   [FIRSTONLY] [NOFORWARD|FORWARD]
   #   [APPEND|NOAPPEND]
   #   [FORMAT=s1] [LFORMAT=s1] [RFORMAT=s1]
   #   [ESAMPLE=n1][SEED=n2]
   #   [RANMETHOD=n]
   #   [UNCONDITIONAL|CONDITIONAL] [OMITTED]
   ntabopt=split("NOPRINT PRINT NOHEADER ONEHEADER ONEHEAD FIRSTONLY NOFORWARD FORWARD NOAPPEND NOAPP APPEND UNCONDITIONAL CONDITIONAL OMITTED",TABOPT)

   idvname=""
   blockstart="{"; blockend="}"; showblockend=1
#   verbatimblockstart="{***"; verbatimblockend="***}"
   verbatimblockstart="<<"; verbatimblockend=">>"
   objstart="{"; objend="}"


   addrcode=0; location="INLINE"
   outputdrop=1

   EPSLEVEL=1; ETALEVEL=2
   EPSTEXT="eps"; ETATEXT="eta"

   TYPECONTINUOUS="continuous"
   TYPELIKELIHOOD="likelihood"
   TYPEM2LL="M2LL"

   TYPEVAR="var"
   TYPESD="sd"
   NORMDIST="Normal"

   #TYPEVAR="variance"
   #NORMDIST="Normal"

  # UNIFDIST="uniform(min=0, max=1,seed="
   UNIFDIST="~Uniform(0,1,"

   INPUTFORMAT="NMTRAN_CODE"

   ELIMNAME=":" # MCL 7 compartment list name (ELIM would be better choice)

   # Data object
   DAT_OBJ="dataObj"
   DAT_DECLARE="DECLARED_VARIABLES"
   data_declare="   "DAT_DECLARE blockstart

   DAT_INPUT="DATA_INPUT_VARIABLES"
   DAT_DERIVED="DATA_DERIVED_VARIABLES"
   DAT_SOURCE="SOURCE"
   DAT_DESIGN="DESIGN"

   # Parameter object
   PAR_OBJ="parObj"
   PAR_MATRIX="matrix"
   PAR_DIAG="diag"
   PAR_SAME="same"
   PAR_STRUC="struc"
   PAR_STRUCTURAL="STRUCTURAL"
   PAR_VARIABILITY="VARIABILITY"

   # Model Object
   MDL_OBJ="mdlObj"
   if (usemcl5obj)
      MDL_INPUT="MODEL_INPUT_VARIABLES"
   else
      MDL_INPUT="COVARIATES" # 
   MDL_IDV="IDV" # usedataidv=0
   MDL_LEVELS="VARIABILITY_LEVELS" # Product 4
   MDL_STRUCPAR="STRUCTURAL_PARAMETERS"
   MDL_VARPAR="VARIABILITY_PARAMETERS"
   MDL_GRPPAR="GROUP_VARIABLES"
   MDL_RVD="RANDOM_VARIABLE_DEFINITION"
   MDL_INDPAR="INDIVIDUAL_VARIABLES"
   MDL_PRIOR="PRIOR_PARAMETERS"
   MDL_PRED="MODEL_PREDICTION"
   MDL_LIB="LIBRARY"
   MDL_DEQ="DEQ"
   MDL_CMT="COMPARTMENT"
   MDL_IDO="CMT_IDO"
   MDL_MIX="MIXTURE"
   MDL_OBS="OBSERVATION"
   MDL_EST="ESTIMATION"
   MDL_SIM="SIMULATION"
   MDL_OUTPUT="MODEL_OUTPUT_VARIABLES"
   # Target application blocks
   MDL_TARGET="TARGET_CODE"
   MDL_NMTRAN="NMTRAN_CODE"
   MDL_MLXTRAN="MLXTRAN_CODE"
   MDL_MATLAB="MATLAB_CODE"
   MDL_PML="PML_CODE"
   MDL_BUGS="BUGS_CODE"
   MDL_R="R_CODE"
   VERBATIM_START=";Verbatim"
   NMTRAN_VERBATIM_CHAR="\""
   OTHER="other "
   LIBNAME="PK"
   LIBRETURN="F"
   LIBPARAM="output"

   # Task object
   TSK_OBJ="taskObj"
   TSK_DAT="DATA"
   TSK_PAR="PARAMETER"

   TSK_TARGET="TARGET"
   DAT_DROP="DROP" 
   DAT_IGNORE="IGNORE"
   DAT_ACCEPT="ACCEPT"
   OUT_ADD="ADD"
   OUT_REMOVE="REMOVE"
   target_app="NMTRAN_CODE"
   target_cmd="WFN"

   # TASK Verbs
   TSK_TASK="TASK"
   TSK_EST="ESTIMATE"
   TSK_SIM="SIMULATE"
   TSK_LIKE="LIKELIHOOD"
   TSK_COV="COVARIANCE"
   if (usefulltask==1)
      TSK_SOLVER="SOLVER"
   else
      TSK_SOLVER="MODEL"
   TSK_IND="INDIVIDUAL"
   TSK_GRF="GRAPHICS"

   # TEL object
   TEL_OBJ="telObj"

   # MOG object
   MOG_OBJ="mogObj"
   MOG_OBJECTS="OBJECTS"
   MOG_MAPPING="MAPPING"
   
   # Pragma
   MDLPRED="MDLPRED"
   MDLIND="MDLIND"
   MCL="@MCL_"
   MCLVERBATIM="\""
   MCLERROR="$ERROR"
   MCLETAPPV="ETAPPV"
} # end BEGIN


NR==1 {
   mdlname=substr(FILENAME,1,length(FILENAME)-4) # remove 3 char extension
   ismdl5=(match(FILENAME,/\.mdl$/)>0); nmdl5=0; nmdlpred6=0; nmdltask6=0; nmdltel6=0; par5_list=""
   if (ismdl5) sub(/_5$/,"",mdlname)
   gsub(/[\-|\+|\.]/,"_",mdlname) # change illegal characters
}

# Uppercase translation is performed by ntcs
#{
#   upcase()
#   rec[++nrec]=$0
#}
ismdl5 {
   if (!match($0,/\# nt2mdl/)) { # Don't include MDL5 nt2mdl line
      # Find NM-TRAN special PK parameters (e.g. ALAG1, F1, R1, D1)
      if (match($0,/^ *ALAG[1-9]*\=/)) {
         split($1,TMP,"=")
         par5_list=par5_list TMP[1] ","
      } else if (match($0,/^ *F[1-9]*\=/)) {
         split($1,TMP,"=")
         par5_list=par5_list TMP[1] ","
      } else if (match($0,/^ *R[1-9]*\=/)) {
         split($1,TMP,"=")
         par5_list=par5_list TMP[1] ","
      } else if (match($0,/^ *D[1-9]*\=/)) {
         split($1,TMP,"=")
         par5_list=par5_list TMP[1] ","
      }
      # Build MDL5 vector
      gsub(/\t/,"   ")
      MDL5[++nmdl5]=$0
   }
   next
}

/^ *; *@MCL_/ { ismcl=1 
#print "ismclset: ismcl="ismcl " ismclignore="ismclignore " $0="$0
}

ismcl {
#print "ismcl: ismcl="ismcl " ismclignore="ismclignore " $0="$0
   if (match($0,/^ *; *@MCL_start_IGNORE/)) {
      ismclignore=1
#print "ismclstart: ismcl="ismcl " ismclignore="ismclignore " $0="$0
   next
   }
   if (match($0,/^ *; *@MCL_end_IGNORE/)) {
      ismclignore=0
      ismcl=0
#print "ismclend: ismcl="ismcl " ismclignore="ismclignore " $0="$0
   next
   }
   if (match($0,/^ *; *@MCL_start_COVARIATES/)) { 
      ismclcov=1
#print "ismclblock: ismclcov="ismclcov " $0="$0
      next
   }
   if (match($0,/^ *; *@MCL_end_COVARIATES/)) { 
      ismclcov=0
#print "ismclblock: ismclcov="ismclcov " $0="$0
      next
   }
#print "ismclblock: ismcl="ismcl " ismclignore="ismclignore " $0="$0
   if (match($0,/^ *; *@MCL_\"/)) {
      ismclverbatim=1
#print "ismclverbatim: ismcl="ismcl " ismclignore="ismclignore " ismclverbatim="ismclverbatim " $0="$0
   } else {
      ismclverbatim=0
   }
}

(ismclignore && !(ismclverbatim || ismclcov)) { $0=";@NMTRAN!"$0
#print "ismclignore: ismclverbatim="ismclverbatim " ismclcov="ismclcov " $0="$0
}


ismclcov {
   if (translate()) { 
      MCLCOVLIST[++nmclcovlist]="   "$0 
      #print "MCLCOVLIST["nmclcovlist"]="MCLCOVLIST[nmclcovlist]
   }
   next
}



match($1,/^\$/) || /ICALL\.EQ\.[3|4]/ || /^ *; *@MCL_\$ERROR/ || /^ *; *ESTBLOCK/{ rectype()}

/^ *IF\( *DVID *\./ {lastDVIDrec=$0}
/^ *F_FLAG *=/ {wasflag=1}
/^ *Y *=/ || match($0,/\) *Y *=/) { getYDVID() } # find names of predictions

{
   if (NF && (istheta || isomega || issigma) ) {
      if (declare()) values()
   } else if ( NF ) {
      if (isinput) {
        addinput()
      } else if (isdata) {
        adddata()
      } else {
        addmodel()
      }
   }
}


END {

#if (! ismdl5) { # NM-TRAN to MDL

   if (ntheta==1 && THETA[1]=="") ntheta=0
   if (nomega==1 && OMEGA[1]=="") nomega=0
   if (nsigma==1 && SIGMA[1]=="") nsigma=0

   # MDL code to allow R syntax checking
      if (addrcode) rstartcode()

   # Generate parameter list used by data object and model object
   makeparlist()
   if (!ndeqline) {
      # Create CMT list using COMPARTMENT sub-block
      compartment_subblock()
   }
   # Write notes
      mcl_notes()
   # Write data object
      data_object()
   # Write parameter object
      parameter_object()
   # Write model object
      model_object()
   # Write task object
      task_object()
   # Write tel object
      tel_object()
   # MDL code to allow R syntax checking
      if (addrcode) rendcode()
   }

#} else { 
#  domdl5()
#}

function mcl_notes() {
   if (nproline) {
      print ""
      for (i=2; i<=nproline; i++) {
         $0=pro_code[i]
         sub(/^ *;/," ")
         print "#" $0
      }
      print ""
   }
}

function splitcomment( icom) {
      icom=index($0,"#")
      if (icom) {
         if (substr($0,icom-1,1)=="\"" && substr($0,icom+1,1)=="\"" && match($0,/ignore *\=/) ) { # ignore="#"
            tmp=substr($0,icom+1)
            icom=icom+index(tmp,"#")
         }
      }
      if (icom) {
         comment=substr($0,icom)
         $0=substr($0,1,icom-1)
      } else
         comment=""
}

function updatemdl5lists(   i,j,pre,nopen,lenrec,iend,icom,endtemp) {
   # change list
   for (i=1; i<=nmdl5; i++) {
      $0=MDL5[i]
      splitcomment()
      if ( ( (j=match($0,   /[\=|\,] *list *\(/   )) ) ) {
         nopen=1
         do {
            if (nopen==0) nopen=1
            pre=substr($0,1,j-1)
            $0=substr($0,j)
            sub(/\= *list *\(/,listconnect listname liststart)
            $0=pre $0
            lenrec=length($0)
            for (k=2; k<=lenrec; k++) {
               if (substr($0,k,1)=="(") {
                  nopen++
#      if (listmax) print "nopen="nopen " "$0
               }
               if (substr($0,k,1)==")") {
                  nopen--
#      if (listmax) print "nopen="nopen " "$0
                  if (nopen==0) {
                     $0=substr($0,1,k-1) listend substr($0,k+1)
                     k=lenrec+1
                  }
               }
            }
            if (nopen) {
               MDL5[i]=$0 comment
               $0=MDL5[++i]
               splitcomment()
               }
         } while (nopen || j=match($0,/[\=|\,] *\list *\(/))
      } # if match list
      MDL5[i]=$0 comment
#print MDL5[i]
   } # end nmdl5 list loop

   # change ode
   for (i=1; i<=nmdl5; i++) {
      $0=MDL5[i]
      splitcomment()
      if ( ( (j=match($0,/[\=|\,] *ode *\(/)) ) ) {
         nopen=1
         do {
            if (nopen==0) nopen=1
            pre=substr($0,1,j-1)
            $0=substr($0,j)
            sub(/\= *ode *\(/,odeconnect "ode" liststart)
            lenrec=length($0)
            for (k=2; k<=lenrec; k++) {
               if (substr($0,k,1)=="(") {
                  nopen++
               }
               if (substr($0,k,1)==")") {
                  nopen--
                  if (nopen==0) {
                     $0=pre substr($0,1,k-1) listend substr($0,k+1)
                     k=lenrec+1
                  }
               }
            }
            if (nopen) {
               MDL5[i]=pre $0 comment
               $0=MDL5[++i]
               splitcomment()
             }
         } while ( j=match($0,/[\=|\,] *\ode *\(/) || nopen)
      } # if match ode
      MDL5[i]=$0 comment
   } # end nmdl5 ode loop

   # change ~
   for (i=1; i<=nmdl5; i++) {
      $0=MDL5[i]
      splitcomment()
      if ( ( (j=match($0,/ *~ *\(/)) ) ) {
         nopen=1
         do {
            if (nopen==0) nopen=1
            pre=substr($0,1,j-1)
            $0=substr($0,j)
            sub(/~ *\(/,"~ " liststart)
            lenrec=length($0)
            for (k=2; k<=lenrec; k++) {
               if (substr($0,k,1)=="(") {
                  nopen++
               }
               if (substr($0,k,1)==")") {
                  nopen--
                  if (nopen==0) {
                     $0=pre substr($0,1,k-1) listend substr($0,k+1)
                     k=lenrec+1
                  }
               }
            }
            if (nopen) {
               MDL5[i]=pre $0 comment
               $0=MDL5[++i]
               splitcomment()
             }
         } while ( j=match($0,/ *\~ *\(/) || nopen)
      } # if match ~
      MDL5[i]=$0 comment
   } # end nmdl5 ~ loop

} # end updatelists

function find_end_block(i,  j,char,nopen) {
   nopen=0
   do {
      $0=MDL5[i]
      for (j=1; j<=length($0); j++) {
         char=substr($0,j,1)
         if (char=="#" && substr($0,j+1,1)!="\"" && !match($0,/ignore *\=/)) {
            j=length($0)+1
         } else {
            if (char=="{") {
               nopen++
#   print "nopen="nopen " "$0
            } else if (char=="}") {
               nopen--
#   print "nopen="nopen " "$0
            }
         }
      }
      i++
   } while (nopen && i<=nmdl5)
   return i-1
}

function mdl5blocks( i,j,k) {
   for (i=1; i<=nmdl5; i++) {
      $0=MDL5[i]
      if (match($0,"= *"DAT_OBJ" *{")) {
         j=i
         k=find_end_block(i)
         MDL5START[DAT_OBJ]=j
         MDL5FINISH[DAT_OBJ]=k
      }
   }
   for (i=1; i<=nmdl5; i++) {
      $0=MDL5[i]
      if (match($0,"= *"PAR_OBJ" *{")) {
         j=i
         k=find_end_block(i)
         MDL5START[PAR_OBJ]=j
         MDL5FINISH[PAR_OBJ]=k
      }
   }
   for (i=1; i<=nmdl5; i++) {
      $0=MDL5[i]
      if (match($0,"= *"MDL_OBJ" *{")) {
         j=i
         k=find_end_block(i)
         MDL5START[MDL_OBJ]=j
         MDL5FINISH[MDL_OBJ]=k
      }
   }
   for (i=1; i<=nmdl5; i++) {
      $0=MDL5[i]
      if (match($0,"^ *"MDL_PRED" *{")) {
         j=i
         k=find_end_block(i)
         MDL5START[MDL_PRED]=j
         MDL5FINISH[MDL_PRED]=k
      }
   }
   for (i=1; i<=nmdl5; i++) {
      $0=MDL5[i]
      if (match($0,"^ *"MDL_LIB" *{")) {
         j=i
         k=find_end_block(i)
         MDL5START[MDL_LIB]=j
         MDL5FINISH[MDL_LIB]=k
      }
   }
   for (i=1; i<=nmdl5; i++) {
      $0=MDL5[i]
      if (match($0," *= *"TSK_OBJ" *{")) {
         j=i
         k=find_end_block(i)
         MDL5START[TSK_OBJ]=j
         MDL5FINISH[TSK_OBJ]=k
      }
   }
   for (i=1; i<=nmdl5; i++) {
      $0=MDL5[i]
      if (match($0," *= *"TEL_OBJ" *{")) {
         j=i
         k=find_end_block(i)
         MDL5START[TEL_OBJ]=j
         MDL5FINISH[TEL_OBJ]=k
      }
   }
}

function mdltask6(  j,k){
   isdeq=0
   for (j=MDL5START[TSK_OBJ]; j<=MDL5FINISH[TSK_OBJ]; j++) {
       $0=MDL5[j]
       gsub(/\t/,"   ")
#print "j="j " "$0
       if (match($0,/ESTIMATE *{/)) {
         istask_estimate=1
         nestline=1
       }
       if (match($0,/SIMULATE *{/)) {
         istask_simulate=1
       }
       if (match($0,/^\*\*\*/)) {
         istask_target=0
       }
       if (istask_target) {
          if (isest_target) est_code[++nestline]=$0
          if (isabb_target) abb_code[++nabbline]=$0
          if (istab_target) tab_code[++ntabline]=$0
          if (ispro_target) pro_code[++nproline]=$0
          if (issim_target) sim_code[++nsimline]=$0
       }
       if (match($0,/\*\*\*$/)) {
         istask_target=1
         isest_target=(index($0,"$EST"))
         isabb_target=(index($0,"$ABB"))
         istab_target=(index($0,"$TAB"))
         ispro_target=(index($0,"$PRO"))
         issim_target=(index($0,"$SIM"))
       }
       if (match($0,/tolrel *=/)) {
          ndeqline=1
          split($0,TMP,"=")
          tol_num=TMP[2]
       }
       if (match($0,/algo *\:/)) {
          split($0,TMP,listconnect)
          methopt=TMP[2]
          gsub(/\"/,"",methopt)
          sub(/\{/,"",methopt)
          sub(/\}/,"",methopt)
       }
   }

   task_object()

}

function mdltel6( j,k){
   for (j=MDL5START[TEL_OBJ]; j<=MDL5FINISH[TEL_OBJ]; j++) {
       $0=MDL5[j]
       #MDLTEL6[++nmdltel6]=$0
   }

   tel_object()

}

function rectype( typ) {
   typ=substr($1,2,3)
   isblock=0
   if ( typ == "THE" || typ== "THI" || typ=="THR") {
      zerotype()
      if ($1=="$THETAI" || typ=="THI" || $1=="$THETAR" || typ=="THR") {
         isthtrans=1
      } else { 
         istheta=1
      }
   } else if ( typ == "OME" ) {
      zerotype()
   if (issigma) print $0
      isomega=1
      issame=index($0,"SAME")
   } else if ( typ == "SIG" ) {
      zerotype()
      issigma=1
      isomega=1
      issame=index($0,"SAME")
   } else if ( typ == "INP" ) {
      zerotype()
      isinput=1
   } else if ( typ == "DAT" ) {
      zerotype()
      isdata=1
   } else if ( typ == "SUB" ) {
      zerotype()
      issub=1
   } else if ( typ == "PK" || typ == "PRE" ) {
      zerotype()
      ispred=1
      if (typ=="PRE")
         pred_location="$PRED"
      else
         pred_location="$PK"
   } else if ( typ == "DES") {
      zerotype()
      isdes=1
#   } else if ( typ == "AES") {
#      zerotype()
#      isaes=1
   } else if ( typ == "MOD") {
      zerotype()
      ismod=1
   } else if ( typ == "MIX") {
      zerotype()
      ismix=1
   } else if ( typ == "PRI") {
      zerotype()
      ispri=1
   } else if ( typ == "ERR" ||  match($0,/^ *; *@MCL_\$ERROR/) ) {
      zerotype()
      iserr=1
      if (match($0,/^ *; *@MCL_\$ERROR/)) {
         $0=";@NMTRAN!"
         ismdlpred=1
      }
      haserror=1 # has had $ERROR
   } else if ( typ == "EST") {
      zerotype()
      isest=1
   } else if ( typ == "COV") {
      zerotype()
      iscov=1
   } else if ( typ == "SIM") {
      zerotype()
      issim=1
   } else if ( typ == "TAB") {
      zerotype()
      istab=1
   } else if ( typ == "PRO" || typ=="SIZ" || typ=="WAR") {
      zerotype()
      ispro=1
   } else if ( typ == "ABB") {
      zerotype()
      isabb=1
   } else if ( index($0,"ICALL.EQ.4")) {
      #zerotype()
      iseq4=1
   } else if ( index($0,"ICALL.EQ.3")) {
      #zerotype()
      isother=1
   } else if ( match($0,/^ *; *ESTBLOCK/) ) {
      #zerotype()
      $1=""; $2=""
   } else {
      #zerotype()
      isother=1
   }
}
        
function zerotype() {
   istheta=0; isthtrans=0; isomega=0; issigma=0; isother=0; isinput=0; isdata=0; iscov=0
   ispred=0; isdes=0; isaes=0; iserr=0; istab=0; isest=0; issim=0
   ismix=0; ismod=0; ismod=0; ispri=0; issub=0; iseq3=0; iseq4=0; isabb=0
   issame=0; ispro=0
}

function addmodel(  i,j,irb,ilb) {

   if (isother) {
      $0=NMTRAN_VERBATIM_CHAR OTHER $0
   } else if (wasother) {
      wasother=0
   }

   if (isabb) {
      abb_code[++nabbline]=$0
      return
   } else if (ispro) {
      pro_code[++nproline]=$0
      return
   } else if (isest) {
      est_code[++nestline]=$0
      if (index(toupper($1),"$EST")==1) $1=""
      if (i=index($0,";")) $0=substr($0,1,i-1)
      estrecord=estrecord" "$0
      return
   } else if (iscov) {
      cov_code[++ncovline]=$0
      if (index(toupper($1),"$COV")==1) $1=""
      if (i=index($0,";")) $0=substr($0,1,i-1)
      covrecord=covrecord " "$0
      return
   } else if (issim) {
      sim_code[++nsimline]=$0
      return
   } else if (isthtrans) {
      trans_code[++ntransline]=$0
      return
   }
   if (index($1,"$")==1) { $1=""; $0=$0 } # remove $RECORD identifier
#   if (ismod) print "addmodel: $0="$0
   isjustpred=(ispred && !(iseq3 || iseq4))
#print "add_model isjustpred="isjustpred" ispred="ispred " $0="$0
   if (isjustpred) {
      if (index(toupper($1),"A_0(")) {
         irb=index($0,"(")
         ilb=index($0,")")
         nazero=substr($0,irb+1,ilb-irb-1)
#print "nazero="nazero" irb="irb" ilb="ilb "diff="ilb-irb-1"|"$0
         $0=substr($0,index($0,"=")+1)

         j=index($0,";")
         if (j) {
            comment=substr($0,j+1,length($0))
            $0=substr($0,1,j-1)
         } else {
            comment=""
         }
         gsub(/  /,"")

         AZERO[nazero]=$0
#print "AZERO["nazero"]="AZERO[nazero]
      } else {
         if (($1=="IF" || match($0,/^ *IF\(/)) && match($0,/\) *Y *=/)) { # convert if() expr to if () then expr endif
            i=match($0,/ *Y *=/)
            pred_code[++npredline]=substr($0,1,i-1) " THEN"
            pred_code[++npredline]=substr($0,i)
            pred_code[++npredline]="ENDIF"
         } else
            pred_code[++npredline]=$0
      }
   } else if (issub) {
      subsave=$0; hasliblist=0
# Look for LIBLIST coded in NM-TRAN input
      j=index($0,";")
      if (j) {
         $0=substr($0,j+1,length($0))
         if (toupper($1)=="LIBLIST") {
            $1=""
            LIBLIST[++nliblist]=$0
            hasliblist=1
         }
      }
      if (!hasliblist) {
         $0=subsave
         sub_code[++nsubline]=$0
         if (advan_num==0) advan_num=getsuboptnum($0,"ADVAN")+0
         if (tran_num==0) tran_num=getsuboptnum($0,"TRANS")+0
         if (tran_num==0) tran_num=getsuboptnum($0,"TRAN")+0
         if (tol_num==0) tol_num=getsuboptnum($0,"TOL")+0
      }
   } else if (ismod) {
      if (index(toupper($1),"COMP")==1) {
         do {
            getcomp()
         } while (index(toupper($1),"COMP")==1)
      } else if (i=index($0,"NCOM")) {
         ncomp=substr($0,i)
         i=index(ncomp,"=")
         ncmt=substr(ncomp,i+1)+0
         for (i=1; i<=ncmt; i++) {
            CMT[i]="AMT"i
            CMLHS[i]=0
         }
      }
      mod_code[++nmodline]=$0
   } else if (ismix) {
      mix_code[++nmixline]=$0
   } else if (ispri) {
      pri_code[++npriline]=$0
   } else if (isdes || isaes) {
      if (index(toupper($1),"DADT")) {
         irb=index($0,"(")
         ilb=index($0,")")
         ndadt=substr($0,irb+1,ilb-irb-1)
         DADT[ndadt]=substr($0,index($0,"=")+1)
      } else {
         des_code[++ndeqline]=$0
      }
   } else if (iseq3) {
      if (match($0,/^ *ENDIF/)) {
         ifcount3--
      } else if (match($0,/^ *IF *\(/) && match($0,/THEN/)) {
         ifcount3++
      }
      if (ifcount3==0) iseq3=0
      eq3_code[++neq3line]=$0
   } else if (iseq4) {
      if (match($0,/^ *ENDIF/)) {
         ifcount4--
      } else if (match($0,/^ *IF *\(/)  && match($0,/THEN/)) {
         ifcount4++
      }
      if (ifcount4==0) iseq4=0
      eq4_code[++neq4line]=$0
   } else if (iserr) {
#print "addmodel: isjustpred $0="$0
       if (($1=="IF" || match($0,/^ *IF\(/)) && match($0,/\) *Y *=/)) { # convert if() expr to if () then expr endif
            i=match($0,/ *Y *=/)
            err_code[++nerrline]=substr($0,1,i-1) " THEN"
            err_code[++nerrline]=substr($0,i)
            err_code[++nerrline]="ENDIF"
      } else {
         err_code[++nerrline]=$0
      }
   } else if (istab) {
      tab_code[++ntabline]=$0
   }
}

function getcomp() {
   sub(/\(/," (")
   if (!(index($1,"=") || $2=="=")) $1="COMP="
   sub(/=/," = ")
   sub(/\(/,"")
   sub(/\)/,"")
   sub(/,/,"")
   CMT[++ncmt]=$3  # COMP = name
   CMLHS[ncmt]=0
   $1=""; $2=""; $3=""
   $0=$0
}

function tidyup(text) {
    gsub(/^=/,"",text)
    gsub(/^\(/,"",text)
    gsub(/\)$/,"",text)
    return logical(text)
}

function logical(text, i) {
# gsub with .AND. does not work
#    gsub(/\.AND\./,"&",text)
   i=match(text,/.\AND\./)
   while (i) {
     text=substr(text,1,i-1) " && " substr(text,i+5)
     i=match(text,/.\AND\./)
   }
   gsub(/\.OR\./," || ",text)
   gsub(/\.EQ\./,"==",text)
   gsub(/\.NE\./,"!=",text)
   gsub(/\.LT\./,"<",text)
   gsub(/\.GT\./,">",text)
   gsub(/\.LE\./,"<=",text)
   gsub(/\.GE\./,">=",text)
   return text
}

function addinput(   i,units) {
# Remove $INPUT
   if (index(toupper($1),"$INP")==1) { $1=""; $0=$0 }
#print "addinput $0="$0
# Remove comments
   i=(index($0,";"))
   units=""
   if (i) {
      units=substr($0,i+1)
      $0=substr($0,1,i-1)
      sub(/ *$/,"")
      i=index(units,"units")
      if (i) {
         units=substr(units,i+5)
         sub(/\=/,"",units)
         sub(/^ */,"",units)
         i=index(substr(units,2),"\"")
#print "addinput: units="units" i="i
         if (i) units=substr(units,1,i+1)
         gsub(/\"/,"",units)
#print "addinput units="units
         $0=$0"!"units"!"
#print "addinput: $0="$0
      }
   }

# Create single input line
   inputline=inputline" "$0
#print "addinput inputline="inputline
}

function adddata(   i) {
# Remove $DATA
   if (index(toupper($1),"$DAT")==1) { $1=""; $0=$0 }
# Remove comments
   i=(index($0,";"))
   if (i) $0=substr($0,1,i-1)

# Create single data line
   dataline=dataline" "$0
}

function replace(var,par, i,pat,tmp,lvar) {
   if (var=="") return
   if ( REVERSE ) {
      tmp=var; var=par; par=tmp
      gsub(/\(/,"\\(",var)
      gsub(/\)/,"\\)",var)
      lvar=length(var)-1
   } else
      lvar=length(var)+1
   pat="[ \\(\\*\\/\\+\\-\\.]" var "[ \\)\\*\\/\\+\\-\\.]"
   $0=" "$0" "
#if (index($0,"THETA(16)")) print "pat: "match($0,pat)" "var" "par" pat="pat" $0="$0
#if (match($0,pat)) print "pat: "match($0,pat)" "var" "par" pat="pat" $0="$0
   while (i=match($0,pat))
      $0=substr($0,1,i) par substr($0,i+lvar)
   $0=substr($0,2,length($0)-2)
}

function find(  j,jomega) {
   for ( j=1; j <= ntheta ; j++ ) {
      if ( ! THLHS[j] ) replace(THETA[j],"THETA("j")")
   }
   jomega=0
   for ( j=1; j <= nomega ; j++ ){
#if (index($0,"ETA(10)")) print "LEVEL["j"]="LEVEL[j] " j="j " par=""ETA("j")"

      if (LEVEL[j]==ETALEVEL && ! OMLHS[j] ) {
         jomega++
         replace("eta_"OMEGA[j],"ETA("jomega")")
      }
   }
   if (!hasid) {
      for ( j=1; j <= nomega ; j++ ) {
         if (! OMLHS[j] ) {
            replace("eps_"OMEGA[j],"ETA("j")")
         }
      }
   } else {
      for ( j=1; j <= nsigma ; j++ ) {
         if (! SILHS[j] ) {
            replace("eps_"SIGMA[j],"ERR("j")")
            replace("eps_"SIGMA[j],"EPS("j")")
         }
      }
   }
   for ( j=1; j <= ncmt ; j++ ) {
      if ( ! CMLHS[j] ) { 
      replace(CMT[j],"A("j")")
      }
   }
 #if (debugerror) print "debugerror: $0="$0
   if ( ! REVERSE ) islhs($0)

}

function translate( i,lhs,comment, j,par,var) {

  if (index($1,NMTRAN_VERBATIM_CHAR)) {
     if (index($1,OTHER)==1) $1=NMTRAN_VERBATIM_CHAR
     return # Do not translate verbatim code
  }

  i=index($0,";")
  if ( i ) {
     comment = substr($0,i)
     $0=substr($0,1,i-1)
  } else
     comment = ""
   if (1==0 && index($0,"MU_")) {
      for ( j=1; j <= nomega ; j++ ) {
         var="MU_"OMEGA[j]; par="MU_"j
         if (REVERSE)
            gsub(par,var)
         else
            gsub(var,par)
      }
   }
  i=index(toupper($0)," EXIT ")
  if (i) {
     errorargs=substr($0,i+6)
     gsub(/ /,",",errorargs)
     sub(/,*$/,"",errorargs)
     if (!useerrorexit)
        exit_comment="#"
     else
        exit_comment=""
     $0=exit_comment substr($0,1,i-1) " errorExit(" errorargs") "
  }
  if (index($1,"IF")==1 && !index($0,"THEN")) {
     sub(/\=/," = ")
     find()
     lhs=""
  } else {
     i=index($0,"=")
     if ( i ) {
        lhs=substr($0,1,i-1)
        $0=substr($0,i+1)
     } else
        lhs = ""
   #print "lhs="lhs," NF="NF
     if ( NF ) {
#if (index($0,"ERR")) print "translate: $0="$0
      find()
#if (index($0,"ERR")) print "translate: $0="$0
     }
  }

  if ( lhs == "" )
     $0 = $0 comment
  else {
     left=lhs
     right=$0
     gsub(/ */,"",left)
     gsub(/ */,"",right)
     # if (left==right) return 0 # remove identical right and left sides
     $0 = lhs "=" $0 comment
  }
  $0=logical($0)

  gsub(/;/,"#")
  sub(/THEN */,"{")
  sub(/ELSE IF */,"} else if {")
  sub(/ELSEIF */,"} else if {")
  sub(/ELSE */,"} else {")
  sub(/ENDIF */,"}")
  sub(/END IF */,"}") # This should be NM-TRAN error
  sub(/IF *\(/,"if (")
  if (match($0,/CALL RANDOM *\( *[0-9] *, *R *\)/)) {
     rantext=" # "$0
     i=match($0,/[0-9]/)
     rannum=substr($0,i)
     rannum=substr(rannum,1,index(rannum,",")-1)
     unifargs="R"UNIFDIST rannum")"
     sub(/CALL RANDOM *\( *[0-9] *, *R *\)/,unifargs)
     $0=$0 rantext
  }
  gsub(/DEXP\(/,"exp(")
  gsub(/EXP\(/,"exp(")
  gsub(/DLOG\(/,"ln(")
  gsub(/LOG\(/,"ln(")
  gsub(/DSQRT\(/,"sqrt(")
  gsub(/SQRT\(/,"sqrt(")
  gsub(/DCOS\(/,"cos(")
  gsub(/COS\(/,"cos(")
  gsub(/DSIN\(/,"sin(")
  gsub(/SIN\(/,"sin(")
#Bob Bauer 5 March 2014: The phi() in NONMEM is the normal cumulative distribution function.  normcdf() or pnorm() should work.  I think pnorm() may be easier to use, with lower.tail set to TRUE.
  gsub(/PHI\(/,"pnorm(")
  if (index($1,"#")!=1 && i=index($0,"GAMLN")) {
#R help: factorial(x) (x! for non-negative integer x) is defined to be gamma(x+1) and lfactorial to be lgamma(x+1).
     j=index(substr($0,i+5),")")
     $0=substr($0,1,i-1) "lfactorial" substr($0,i+5,j-1) "-1)" substr($0,i+5+j+1)
     sub(/ *\+ *1 *\-1/,"",$0) # e.g. lfactorial(DV+1-1) -> lfactorial(DV)
  }
  if (!index($0,"***")) gsub(/\*\*/,"^")
  return 1

}

function islhs(lhs, j,svln,k) {
   svln=$0
   $0=lhs
   for (k=1; k<=NF; k++) {
      for ( j=1; j <= ntheta ; j++ ) {
        if ($k == THETA[j]) THLHS[j]=1
      }
      for ( j=1; j <= nomega ; j++ ) {
        if ($k == OMEGA[j]) OMLHS[j]=1
      }
      for ( j=1; j <= nsigma ; j++ ) {
        if ($k == SIGMA[j]) SILHS[j]=1
      }
      for ( j=1; j <= ncmt ; j++ ) {
        if ($k == CMT[j]) CMLHS[j]=1
      }
   }
   $0=svln
}

function omega_options(isblock,line,  i,comment) {
      i=index(line,";")
      if (i) {
         comment=substr(line,i)
         line=substr(line,1,i-1)
      } else {
         comment=""
      }
      # Is there a FIX in the $OMEGA BLOCK line?
      if (i=index(toupper(line),"FIX")) {
         if (isblock)
            OMBLKFIX[nomblk]=1
         else
            OMFIX[nomega]=1
#print "omega_options: nomega="nomega "nomblk="nomblk " isblock="isblock" line="line " OMBLKFIX["nomblk"]="OMBLKFIX[nomblk]
         if (index(toupper(line),"FIXED"))
            line=substr(line,1,i-1) substr(line,i+length("FIXED"))
         else
            line=substr(line,1,i-1) substr(line,i+length("FIX"))
      }

      # Is there a COVARIANCE in the $OMEGA BLOCK line?
      if (i=index(toupper(line),"COVARIANCE")) {
         line=substr(line,1,i-1) substr(line,i+length("COVARIANCE"))
         if (isblock) {
            OMBLKVAR[nomblk]=1
            OMBLKSTD[nomblk]=0
         } else {
            OMVAR[nomega]=1
            OMSTD[nomega]=0
         }
      }
      # Is there a VARIANCE in the $OMEGA  line?
      if (i=index(toupper(line),"VARIANCE")) {
         line=substr(line,1,i-1) substr(line,i+length("VARIANCE"))
         if (isblock) {
            OMBLKVAR[nomblk]=1
            OMBLKSTD[nomblk]=0
         } else {
            OMVAR[nomega]=1
            OMSTD[nomega]=0
         }
      }
      # Is there a STANDARD in the $OMEGA  line?
      if (i=index(toupper(line),"STANDARD")) {
         line=substr(line,1,i-1) substr(line,i+length("STANDARD"))
         if (isblock) {
            OMBLKVAR[nomblk]=0
            OMBLKSTD[nomblk]=1
         } else {
            OMVAR[nomega]=0
            OMSTD[nomega]=1
         }

      } else if (i=index(toupper(line),"SD")) {
         line=substr(line,1,i-1) substr(line,i+length("SD"))
         if (isblock) {
            OMBLKVAR[nomblk]=0
            OMBLKSTD[nomblk]=1
         } else {
            OMVAR[nomega]=0
            OMSTD[nomega]=1
         }

      }
      # Is there a CORRELATION in the $OMEGA BLOCK line?
      if (i=index(toupper(line),"CORRELATION")) {
         line=substr(line,1,i-1) substr(line,i+length("CORRELATION"))
         if (isblock) {
            OMBLKVAR[nomblk]=0
            OMBLKSTD[nomblk]=1
         } else {
            OMVAR[nomega]=0
            OMSTD[nomega]=1
         }
      }

      return line comment
}

function declare( i,n,nf,name,names,lhs,dupname,j,cleanline) {
# assumes format
# $TYPE values ;[n] name [n]
   sub(/^ */,"") # remove leading blanks
   i=index($0,";")
#fix up if no blank before "("
   j=index($0,"(")
   if (j<i) { sub(/\(/," ("); i=index($0,";") }
   if ( i==1 && issame) {
      $0="0"substr($0,2) # Prepend 0 allows format ;;BSVx
   }
   i=index($0,";")
   if (i==1) return 0
   isblockline=index($0,"BLOCK")
   if (isblockline) {
      nomblk++
      # Remove BLOCK(n)
      j=index($0,")")
      $0=substr($0,j+1)
      # Are there other options in the BLOCK line?
      $0=omega_options(1,$0)
      # find index of a comment if it exists
      i=index($0,";")
      if (i) $0=substr($0,1,i-1) # remove any comment on BLOCK line 
      isblock=1
   }
   if (isblock) {
      if (i)
         blockline=substr($0,1,i-1)
      else
         blockline=$0
      # Are there options in any remaining block lines?
      blockline=omega_options(1,blockline)
      # default for NM-TRAN is VARIANCE and COVARIANCE
      if (OMBLKSTD[nomblk]!=1) {
         OMBLKVAR[nomblk]=1
         if (MLX_omega_type=="") MLX_omega_type="yes"
      } else {
         if (MLX_omega_type=="") MLX_omega_type="no"
      }
   }
   if (index($1,"$")==1) {
      isblock=0
      $1="" # Remove record type
#      if (isomega && (nomblk==0 || OMBLK[nomega])) nomblk++ # diagonal omega block start
      if (isomega && nomblk==0) nomblk++ # diagonal omega block start
      i=index($0,";")
   }
   if (issame && i) {
#print "declare i="i" $0="$0
      tmp=substr($0,i+1)
      if (j=index(tmp,";")) $0=substr($0,1,j-1)
#print "declare j="j" $0="$0
      $0=substr($0,i+1)  # remove ; and anything before the "; NAME". This affects omega values.
#print "declare i="i" $0="$0
      i=index($0,";")
      $0="0;"substr($0,i) # create 0;BOVname
#print "declare SAME i="i " "$0
      i=index($0,";")
   }
#if (issame) print "SAME i="i " "$0

#   print "$0=|"$0"|"
   cleanline=$0
   sub(/FIX[ED]*/,"",cleanline) # Remove FIX or FIXED
   sub(/SAME/,"",cleanline) # Remove SAME
   sub(/^ */,"",cleanline) # Remove leading blanks
#   print "cleanline=|"cleanline"|"


   if (i==0) {
      return 0
      $0=$0 "; 0." # no comment so no name
      i=index($0,";")
   }

   if (cleanline=="") return 0 # no comment so no name
#print "declare="$0

   nametext=substr($0,i+1)
   nf=split(nametext,names)
#print "nf="nf" names="toupper(substr($0,i+1))
   parcomment=" #"
   if (nf>1 && match(names[1],/^[0-9]+$/)) {
      name=names[2]
      j=3
   } else {
      name=names[1]
      j=2
   }
   name=toupper(name)
   for (i=j; i<=nf; i++) {
      parcomment=parcomment" "names[i]
   }
#if (issame) print "name="name " nomega="nomega" nomblk="nomblk
#print "name0="name
   if ( match(name,/^[0-9]+$/) ) return 0 # ignore numeric 
   i=match(name,/\(|\[|\{/)
   if (i) name=substr(name,i+1) # ignore ( [ {  before name
#print "name1="name
   i=match(name,/\)|\]|\}|\(|\[|;|\{|,/)
   if (i) name=substr(name,1,i-1) # ignore ( [ { } ] ) or ; after name
   gsub(/~/,"",name) # remove ~ which may be used to indicate a distribution parameter
#print "name2="name
   if ( !match(name,/^[a-zA-Z0-9]/) ) return 0 # ignore non-alphanumeric
   gsub(/\./,"_",name) # replace invalid chars with underscore
   if (parcomment==" #") parcomment=""
   if (index(parcomment,"units")) {
      parcomment=substr(parcomment,1,i-1)
      i=index(substr(parcomment,2),"\"")
      if (i) parcomment=substr(parcomment,1,i+1)
   }
   if ( isnewname(name) ) {
      if ( istheta ) {
         if (match(name,/^[0-9]/)) name="THETA" ntheta+1
         if (!(index(name,"POP")==1 || index(name,"RUV")==1)) name="POP_"name
         THETA[++ntheta]=name
         THLHS[ntheta]=0
         THCOM[ntheta]=parcomment
      } else if ( isomega ) {
#print "name="name
         if (issigma) { 
            if (match(name,/^[0-9]/)) name="EPS" nsigma+1
            if (!index(name,"RUV")==1) name="RUV_"name
         } else {
            if (match(name,/^[0-9]/)) name="ETA" nomega+1
            if (!index(name,"PPV")==1 && !index(name,"BSV")==1 && !index(name,"BOV")==1 && !index(name,"BTV")==1 && !index(name,"RUV")==1) name="PPV_"name
            if ((index(name,"PPV")==1 || index(name,"BSV")==1 || index(name,"BOV")==1 || index(name,"BTV")==1 || index(name,"RUV")==1) && index(name,"_")!=4) name=substr(name,1,3)"_"substr(name,4)
         }
         OMEGA[++nomega]=name
         OMLHS[nomega]=0
         OMBLK[nomega]=isblock
         if (isblock)
            NOMBLK[nomega]=nomblk
         else
            NOMBLK[nomega]=0
#print "OMEGA["nomega"]="OMEGA[nomega]" NOMBLK["nomega"]="NOMBLK[nomega] 
         OMCOM[nomega]=parcomment
         if (! issame)
            sameblock=nomblk
         else
            OMBLKSAME[nomega]=sameblock
         if (issigma || index(name,"RUV_")==1) { # If there is no ID then OMEGA is used as Level 1
#print "OMEGA["nomega"]="OMEGA[nomega]" name="name " issigma="issigma
            LEVEL[nomega]=EPSLEVEL
         } else
            LEVEL[nomega]=ETALEVEL

      }
      # SIGMA[] is used to build the random variable definition block and replace ERR|EPS
      if (issigma || (isomega && (index(name,"RUV_")==1)) ) {
         SIGMA[++nsigma]=name
         SILHS[nsigma]=0
      }
   }
   return 1
}

function isnewname(name, j, dupname,i) {
   dupname=0
   if ( name=="F") return 0 # reserved name
   for ( j=1; j <= ntheta ; j++ ) {
      if (name==THETA[j]) dupname=1
   }
   for ( j=1; j <= omega ; j++ ) {
      if (name==OMEGA[j]) dupname=1
   }
   for ( j=1; j <= nsigma ; j++ ) {
      if (name==SIGMA[j]) dupname=1
   }
   return (! dupname)
}
    
function upcase( i,ucln) {
#Don't mess with verbatim code
   if (match($0,/^ *\"/)) return
#Avoid changing case on records with file names
   ucln=toupper($0)
   if (match(ucln,/MSFO\=/)) {
      i=index(ucln,"MSFO=")
      $0=toupper(substr($0,1,i+4)) substr($0,i+5)
   } else if (match(ucln,/FILE\=/)) {
      i=index(ucln,"FILE=")
      $0=toupper(substr($0,1,i+4)) substr($0,i+5)
   } else if (match(ucln,/\$SUB/)) {
      i=index(ucln,"=")
      $0=toupper(substr($0,1,i)) substr($0,i+1)
   } else if (match(ucln,/\$DAT/)) {
      for (i=1; i<=NF; i++)
         if (i != 2) $i=toupper($i)
      $0=$0
   } else if (! (match(ucln,/\$MSFI/)||match(ucln,/\$DAT/))) {
      i=index(ucln,";")
      if ( i )
        $0=toupper(substr($0,1,i-1)) substr($0,i)
      else
        $0=toupper($0)
   }
   gsub(/\t/,"   ")
}

function removeclash( i,j,k) {
# prevent clash between data item name in $INPUT and parameter name
   for (i=1; i<=ninput; i++) {
#       print name,i,INPUT[i]
       item=INPUT[i]
       for (j=1; j<=ntheta; j++) {
          if (THETA[j]==item) THETA[j]="F"item
       }
       for (j=1; j<=nomega; j++) {
          if (OMEGA[j]==item) OMEGA[j]="F"item
       }
       for (j=1; j<=ntheta; j++) {
          if (SIGMA[j]==item) SIGMA[j]="F"item
       }
   }
}

function values(  i,units) {
# $0 format is values [FIX] [SAME] [; [name] comment]
   i=index($0,";")
   if (i) {
      if (i==1) return # This should not happen!
      units= substr($0,i+1)
      $0=substr($0,1,i-1) # Remove [name] comment 
      i=index(units,"units")
      if (i) {
         units=substr(units,i+5)
         sub(/\=/,"",units)
         sub(/ /,"",units)
         i=index(substr(units,2),"\"")
         if (i) units=substr(units,1,i+1)
      } else {
         units=""
      }
   }
# print "values $0=|"$0"|"
#   if (isblockline) return
   if (istheta) {
#      if (index($1,"$THE")) $1=""
      gsub(/ /,"")
      $0=tidyup($0) # remove ( and )
      i=index(toupper($0),"FIX")
      if (i)
        THFIX[ntheta]=" ,fix=true"
      else
        THFIX[ntheta]=""
      sub(/FIX[ED]*/,"") # Remove FIX or FIXED
      gsub(/\(/,"")
      gsub(/\)/,"")
      i=split($0,VALS,",")
      if (i==1) {
        THLO[ntheta]=""
        THVAL[ntheta]=VALS[1]
        THHI[ntheta]=""
      } else {
        THLO[ntheta]=VALS[1]
        THVAL[ntheta]=VALS[2]
        THHI[ntheta]=VALS[3]
      }
      THUNITS[ntheta]=units
   }

   if (isomega) {
#      if (index($1,"$OME")) $1=""
#      if (index($1,"$SIG")) $1=""
      isblockline=OMBLK[nomega]
      if (isblockline) {
         i=index($0,")")
         if (i) $0=substr($0,i+1)
         sub(/FIX[ED]*/,"") # Remove FIX or FIXED
         gsub(/ *$/,"") # remove trailing blanks
         gsub(/  /," ") # replace 2 blanks with blank
         gsub(/ /,",") # replace blanks with commas
         if ($0=="") return
         gsub(/  /," ")
         nomline=split($0,OMLINE,",")
#   print "OMVAL=|"$0"| nomline="nomline
         iscov=0
         for (i=1; i<nomline; i++) {
            if (OMLINE[i]+0) iscov=1
#   print "OMLINE["i"]=|"OMLINE[i]"| iscov="iscov
         }
         OMCOV[NOMBLK[nomega]]=iscov
         if (OMBLKSAME[nomega]) {
   #      print "OMBLKSAME["nomega"]="OMBLKSAME[nomega]
   #      OMVAL[nomega]="SAME[block"OMBLKSAME[nomega]"]"
            OMVAL[nomega]="SAME"
         } else if (!iscov && nomline)
            OMVAL[nomega]=OMLINE[nomline]
         else
            OMVAL[nomega]=$0

#print "OMCOV[NOMBLK["nomega"]]="OMCOV[NOMBLK[nomega]]
#print "isomega|"$0"|"
      } else {
         $0=omega_options(0,$0)
         gsub(/  /," ")
         OMVAL[nomega]=$0
      }
#if (OMVAL[nomega]!="SAME") print "values: OMEGA["nomega"]="OMEGA[nomega] " OMVAL["nomega"]="OMVAL[nomega] " isblockline="isblockline" $0=|"$0"|"
      if (useunits && units!="")
         OMUNITS[nomega]=", units="units
      else
         OMUNITS[nomega]=""
   }

}

function findpat(lookfor, i,code,var,pat,j,ipat) {
   ipat=0
   # Look for ETA, ERR, EPS in the code
   if (match($0,/^; *Y *=/) )
     i=0
   else if (match($0,/^ *;@NMTRAN!/)) {
     i=index($0,"!")
     code=substr($0,i+1)
     i=0
   } else
      i=index($0,";")
   if (i!=1) {
    if (i!=0)
       code=substr($0,1,i-1)
    else
       code=$0
    code=code ")" # make sure match finds closing char
    if (lookfor=="ETA")
       nvar=nomega
    else
       nvar=nsigma
    for ( j=1; j <= nvar ; j++ ) {
       var=lookfor"("j")"
       gsub(/\(/,"\\(",var)
       gsub(/\)/,"\\)",var)
       pat="[ \\(\\*\\/\\+\\-\\.=]" var "[ \\)\\*\\/\\+\\-\\.$]"
#print "code="code" pat="pat
       if (ipat=match(code,pat)) {
          j=nvar+1
       }
    }
   }
#print "ipat="ipat
   return ipat
}
function estoptions() {
   if (nestline) {   
      if (i=index(estrecord,";"))
         $0=substr(estrecord,1,i-1)
      else
         $0=estrecord
      nestopt=split($0,ESTOPT)

      methopt=""; maxeval=""; nsig=""
      esttype=TYPECONTINUOUS; estrectype=TYPECONTINUOUS
      for (i=1; i<=nestopt; i++) {
         estopt=toupper(ESTOPT[i])
         if (index(estopt,"LIKE")==1) {
            estrectype=TYPELIKELIHOOD
         } else if (index(estopt,"-2LL")==1)  {
            estrectype=TYPEM2LL
         }
         if (index(estopt,"MAX")==1) {
            if (j=index(estopt,"=")) {
               maxeval=substr(estopt,j+1)
            } else {
               maxeval=ESTOPT[i+1]
            }
         } else if (!index(estopt,"SIGL")==1 && (index(estopt,"SIG")==1 || index(estopt,"NSIG")==1)) {
            if (j=index(estopt,"=")) {
               nsig=substr(estopt,j+1)
            } else {
               nsig=ESTOPT[i+1]
            }
         } else if (index(estopt,"METH")==1) {
            if (j=index(estopt,"=")) {
               method=substr(estopt,j+1)
               if (index(method,"COND")==1) method="FOCE"
            } else {
               method=ESTOPT[i+1]
               sub(/\=/,"",method)
            }
            if (method+0==1)
               method="FOCE"
            else if (method==0)
               method="FO"
               
            methopt=methopt method " "
         } else if (index(estopt,"INTER")==1) {
               methopt=methopt " " estopt
         }
      }
   }
}

function findpred(pred_code, npredline,   ifcount,i,j,hasy,ifrec,endifrec) {
   ifcount=0
   for (i=1; i<=npredline; i++) {
      predrec=toupper(pred_code[i])
#print "findpred ALL pred_code["i"]="pred_code[i] " hasy="hasy " ifcount="ifcount
      if (match(predrec,/^ *ENDIF/) || match(predrec,/^ *END IF/) ) {
         ifcount--
#print "findpred ENDIF ifcount="ifcount" predrec="predrec
         if (hasy) {
            hasy=0
            endifrec=i
            for (j=ifrec; j<=endifrec; j++) {
               jpredrec=pred_code[j]
               lik_code[++nlikline]=jpredrec
               pred_code[j]="#DROP"
#print "findpred ENDIF j="j" nlikline="nlikline" lik_code["nlikline"]="jpredrec
           }
         }
      } else if (match(predrec,/^ *IF *\(/) && match(predrec,/THEN/) ) {
         ifcount++
#print "findpred IF i="i" 
         ifrec=i # Index of first record in if block
      } else if (match(predrec,/^ *Y *=/)) {
         if (ifcount) { # if blocks contain Y
            hasy=1
         } else { # Add any Y records not in if blocks
            lik_code[++nlikline]=pred_code[i]
            pred_code[i]="#DROP"
#print "findpred Y nlikline="nlikline" lik_code["nlikline"]="pred_code[i]
         }
      }
   }
}

####  MDL Output Functions ####

function rstartcode() {

print "#### The following code is not part of the MDL. It is used when this script is executed in the R environment in order to check the R syntax used in the MDL."

print "### Functions to test R syntax"
print "mdl = function(m) {"
print "	m = substitute(m)"
print "	m"
print "}"

print "writemdl = function(nam, m){"
print "	model = paste(mdl_dir , \"/\", nam, \".txt\", sep=\"\")"
print "	sink(model)"
print "	cat(nam, \"()\\n\", sep=\"\")"
print "	print(m)"
print "	sink()"
print "}"

print "### End of R syntax checking code"

print ""
print "## The MCL is used to declare data, parameter, model and task objects."
print ""
}


function data_object( i,j) {
   gsub(/ *= */,"=",inputline)
   ninput=split(inputline,INPUT)
   for (i=1; i<=ninput; i++) {
#print "data_object INPUT["i"]="INPUT[i]
      nunit=split(INPUT[i],UNITS,"!")
      if (nunit>1) {
         INPUT[i]=UNITS[1]
         DATUNITS[i]=UNITS[2]
      }
      if (j=index(INPUT[i],"=DROP")) {
         INPUT[i]=substr(INPUT[i],1,j-1)
         DROP[i]=1
      } else if (INPUT[i]=="DROP") {
         INPUT[i]="DROP"i
         DROP[i]=1
      } else {
         DROP[i]=0
      }
   }

   removeclash()

# Find use=* cols
   hascols()

   ndropline=0; ndataline=0; nignoreline=0; nacceptline=0; headerline=""
   data_options=""; head_offset="\n      "
   for (i=1; i<=ninput; i++) {
       item=toupper(INPUT[i])
          if (idvname=="") {
             if (item=="TIME") 
                idvname=item
             else if (item=="CONC") 
                idvname=item
             else if (item=="CP") 
                idvname=item
          }
    }
    if (idvname=="") idvname="T"

   for (i=1; i<=ninput; i++) {
       item=INPUT[i]
       sub(/ *$/,"",item) # remove trailing blanks
       if (item!="") {
          if (j=index(item,"=")) {
             ALIAS[i]=substr(item,j+1)
             INPUT[i]=substr(item,1,j-1)
          } else {
             ALIAS[i]=""
          }
          isdata=1
   #print "data_object INPUT["i"]="INPUT[i]" DATUNITS["i"]="DATUNITS[i]
          if (DROP[i]) {
             drop_code[++ndropline]=INPUT[i]
             headerline=headerline head_offset data_type(isdata,!usemcl5obj,INPUT[i],ALIAS[i],DATUNITS[i])
          } else if (toupper(INPUT[i])=="DROP") {
             drop_code[++ndropline]="DROP"i
             headerline=headerline head_offset "DROP"i
             DROP[i-1]=1
          } else {
            headerline=headerline head_offset data_type(isdata,!usemcl5obj,INPUT[i],ALIAS[i],DATUNITS[i])
          }
       }
   }
   if (idvname=="") idvname=idvname2

# Identify data options in the data record and remove them from the dataline
# so that only relevant options (filename, IGNORE, ACCEPT) are left
# A list of data_options is built to be noted as a commment
   for (i=1; i<=ndatopt; i++) {
      if (index(dataline,DATOPT[j])) {
         sub(DATOPT[j],"",dataline)
         data_options=data_options DATOPT[j] " "
      }
   }

   data_code[++ndataline]="   "DAT_INPUT blockstart
   data_code[++ndataline]=substr(headerline,2)

   if (showblockend) data_code[++ndataline]="   "blockend"# end "DAT_INPUT
   if (data_options!="") {
      data_code[++ndataline]=""
   }
   ndata=split(dataline,DATA)

   for (i=2; i<=ndata; i++) {
       item=DATA[i]
       edittext="IGNORE"
       if (index(toupper(item),edittext)) {
          remainder=substr(item,length(edittext)+1)
          if (remainder=="") remainder=DATA[i+1]
          remainder=tidyup(remainder)
          if (length(remainder)==1) 
             ignorechar=remainder
          else
             ignore_code[++nignoreline]=remainder
       } else {
          edittext="IGN"
          if (index(toupper(item),edittext)) {
             remainder=substr(item,length(edittext)+1)
             if (remainder=="") remainder=DATA[i+1]
             remainder=tidyup(remainder)
             if (length(remainder)==1) 
                ignorechar=remainder
             else
                ignore_code[++nignoreline]=remainder
          }
       }
       edittext="ACCEPT"
       if (index(toupper(item),edittext)) {
          remainder=substr(item,length(edittext)+1)
          if (remainder=="") remainder=DATA[i+1]
          accept_code[++nacceptline]=tidyup(remainder)
       } else {
          edittext="ACC"
          if (index(toupper(item),edittext)) {
             remainder=substr(item,length(edittext)+1)
             if (remainder=="") remainder=DATA[i+1]
             accept_code[++nacceptline]=tidyup(remainder)
          }
       }
   }

   if (ndropline || nignoreline || nacceptline) {
      edit_code[++neditline]="   "TSK_DAT blockstart
      if (nignoreline) {
         if_list=DAT_IGNORE"=if("
         for (i=1; i<nignoreline; i++) {
            if_list=if_list ignore_code[i]","
         }
         if_list=if_list ignore_code[nignoreline]
         gsub(/,/," || ",if_list)
         edit_code[++neditline]="      "if_list")"
      }
      if (nacceptline) {
         if_list=DAT_ACCEPT"=if("
         for (i=1; i<nacceptline; i++) {
            if_list=if_list accept_code[i]","
         }
         $0=accept_code[nacceptline]
         if_list=if_list $0
#         gsub(/,/," && ",if_list) # replaces && with ,, (!)
         i=match(if_list,/,/)
         while (i) {
           if_list=substr(if_list,1,i-1) " && " substr(if_list,i+1)
           i=match(if_list,/,/)
         }

         edit_code[++neditline]="      "if_list")"
      }
      if (ndropline) { 
         if (!outputdrop) {
            edit_code[++neditline]="# "DAT_DROP" not yet implemented in MDL Editor"
         } else {
            edit_code[++neditline]="      "DAT_DROP listconnect listname liststart 
            for (i=1; i<ndropline; i++) {
               edit_code[++neditline]="         "drop_code[i]","
            }
            edit_code[++neditline]="         "drop_code[ndropline]
            edit_code[++neditline]="      " listend " # end "DAT_DROP
         }
      }
      if (showblockend) edit_code[++neditline]="   "blockend"# end "TSK_DAT
   }

   if (ndata) {
      data_code[++ndataline]="   "DAT_SOURCE blockstart""
      if (usedataname) {
         datasep=","
         file_indent="\n      "
      } else {
         datasep=""
         file_indent="      "
      }
      file_list=file_indent "file=\""DATA[1]"\"" datasep
      file_indent="\n      "
      file_list=file_list file_indent"inputFormat is nonmemFormat"
      if (useignore && ignorechar!="") file_list=file_list datasep file_indent"ignore=\""ignorechar"\""
      if (usedataname)
         data_code[++ndataline]="      myData"listconnect listname liststart file_list listend
      else
         data_code[++ndataline]=file_list
      #data_code[++ndataline]="      INLINE{}"
      if (showblockend) data_code[++ndataline]="   "blockend"# end "DAT_SOURCE
   }
   if (ndata || ninputline) {
      data_obj[++ndataobj]=mdlname"_dat = "DAT_OBJ objstart
      if (ishelp) {
         data_obj[++ndataobj]="### Data object"
      }
      # ninputline and input_code[] not currently in use (17 May 2014)
      # for (i=1; i<=ninputline; i++ ) {
      #   data_obj[++ndataobj]=input_code[i]
      #}

      # DECLARED_VARIABLES block
      if (!usemdl5obj) {
         data_obj[++ndataobj]=""
         if (ishelp) {
            data_obj[++ndataobj]="# Model object variables that are required in the data object."
         }
         sub(/, *$/,"",data_declare)
         data_obj[++ndataobj]=data_declare blockend
         data_obj[++ndataobj]=""
      }
      if (ishelp) {
         data_obj[++ndataobj]="# The data object specifies a table of columns of data variables and the variable names which will be also used by the model object"
      }

      #DATA INPUT VARIABLES and SOURCE block
      for (i=1; i<=ndataline; i++ ) {
         data_obj[++ndataobj]=data_code[i]
      }
      data_obj[++ndataobj]=objend" # end data object"
      data_obj[++ndataobj]=""

      for (i=1; i<=ndataobj; i++ ) {
         $0=data_obj[i]
         sub(/mg\/h\/70kg/,"mg/h/kg*70")
         sub(/L\/h\/70kg/,"L/h/kg*70")
         sub(/L\/70kg/,"L/kg*70")
         print $0
      }
   }
}

function parameter_object(   i,j,lastcovmatnum,covmatnum,covnamek,covnamekk,etaparlist,etavallist,etacorrtype,hascorreps,epsparlist,epsvallist,epscorrtype) {
   if (!(ntheta || nomega || nsigma)) return 
   nparmobj=0
   parm_obj[++nparmobj]=mdlname"_par = "PAR_OBJ objstart
   if (ishelp) {
      parm_obj[++nparmobj]="### Parameter object"
      parm_obj[++nparmobj]="# The parameter object defines the numerical values of the parameters with optional constraints (lo and high values) and units."
   }
   parm_obj[++nparmobj]=""
   if (ntheta) {
      parm_obj[++nparmobj]="   "PAR_STRUCTURAL blockstart""
      if (ishelp) parm_obj[++nparmobj]="# The "PAR_STRUCTURAL" block define the structural model parameters."
      if (isnmtran) parm_obj[++nparmobj]="      #THETA"
      for (i=1; i<=ntheta; i++) {
         if (THLO[i]!="") THLO[i]=", lo="THLO[i]
         if (THHI[i]!="") THHI[i]=", hi="THHI[i]
         if (useunits) {
            if (THUNITS[i]!="") THUNITS[i]=", units="THUNITS[i]
         } else {
            THUNITS[i]=""
         }
         if (THVAL[i]!="") {
            parm_obj[++nparmobj]="      "THETA[i] listconnect listname liststart "value="simplify(THVAL[i]) THLO[i] THHI[i] THFIX[i] THUNITS[i] listend THCOM[i]
         }
      }
      if (showblockend) parm_obj[++nparmobj]="   "blockend"# end "PAR_STRUCTURAL
      parm_obj[++nparmobj]=""

      if (ntransline) {
         open_target(MDL_NMTRAN,"$THETA",",last=true")
         for (i=1; i<=ntransline; i++) {
            $0=trans_code[i]
            target_code[++ntargetcode]=NMTRAN_VERBATIM_CHAR "THET " $0
         }
      close_target("parm")
      parm_obj[++nparmobj]=""

      }
   }
   if (nomega || nsigma) {
      parm_obj[++nparmobj]="   "PAR_VARIABILITY blockstart""
      if (ishelp) {
         if (iscovmatrix) parm_obj[++nparmobj]="# The "PAR_VARIABILITY" block has a more complex structure because it needs to express a lower triangular matrix of parameters and how they are related to each other."
         parm_obj[++nparmobj]="# "PAR_VARIABILITY" parameters may be expressed with a type of \"" TYPESD "\" which implies standard deviations on the diagonal and correlations"
         parm_obj[++nparmobj]="# on the lower off diagonal elements or \"" TYPEVAR "\" which implies variances and covariances."
      }

      printparms(ETALEVEL)
      printparms(EPSLEVEL)

      # Print correlations 
      if (!iscovmatrix) {
         pat_declare=""
         lastcovmatnum=0
         parm_obj[++nparmobj]=""
         if (ncorrlist) parm_obj[++nparmobj]="# Matrix off-diagonal values"
         for (i=1; i<=ncorrlist; i++) {
            covmatnum=COVMATNUM[i]
            if (covmatnum!=lastcovmatnum) {
               if (covmatnum>1) {
                  printcorr(covmatnum,etaparlist,etavallist,etacorrtype,hascorreps,epsparlist,epsvallist,epscorrtype)
               }
               hascorreps=0
               etaparlist=""
               etavallist="value=["
               epsparlist=etaparlist
               epsvallist=etavallist
               lastcovmatnum=covmatnum
            }

            covnamek=CORR_RVS[i,1]
            covnamekk=CORR_RVS[i,2]
            if (index(covnamek,"eta_")==1) {
               etacorrtype=CORRTYPE[i]
               etaparlist=etaparlist covnamek"," covnamekk","
               etavallist=etavallist CORRVALUE[i]","
            } else {
               hascorreps=1
               epscorrtype=CORRTYPE[i]
               epsparlist=epsparlist covnamek"," covnamekk","
               epsvallist=epsvallist CORRVALUE[i]","
            }
         }
         printcorr(covmatnum+1,etaparlist,etavallist,etacorrtype,hascorreps,epsparlist,epsvallist,epscorrtype)
      }

      parm_obj[++nparmobj]=""

      if (showblockend) parm_obj[++nparmobj]="   "blockend"# end "PAR_VARIABILITY
      parm_obj[++nparmobj]=""
   }
   
   if (npriline) priorblock()

   parm_obj[++nparmobj]=objend" # end of parameter object"
   parm_obj[++nparmobj]=""

   for (i=1; i<=nparmobj; i++ ) {
      $0=parm_obj[i]
      if (index($1,NMTRAN_VERBATIM_CHAR)==1 && length($1)>1) { $1=""; sub(/ */,"") }
      sub(/mg\/h\/70kg/,"mg/h/kg*70")
      sub(/L\/h\/70kg/,"L/h/kg*70")
      sub(/L\/70kg/,"L/kg*70")
      print $0
      if (!iscovmatrix && i==1 && pat_declare!="") {
         print ""
         print "   " DAT_DECLARE blockstart pat_declare blockend
      }
   }

   # Task related properties for PARAMETER object
      if (1==0) { # no reason for this block when generated by nt2mdl
         edit_code[++neditline]="   "TSK_PAR blockstart
         if (showblockend) edit_code[++neditline]="   "blockend"# end "TSK_PAR
      }

}

function printcorr(covmatnum,etaparlist,etavallist,etacorrtype,hascorreps,epsparlist,epsvallist,epscorrtype, matnum,i,j,netaparlist,nparlist,gotpar,etapar,ppvparlist,nepsparlist,epspar,ruvparlist) {
         matnum=covmatnum-1
         netaparlist=split(substr(etaparlist,1,length(etaparlist)-1),ETAPARLIST,",")
         if (netaparlist) {
            ppvparlist="parameter=["; nparlist=0
            for (i=1; i<=netaparlist; i++) {
               etapar=ETAPARLIST[i]
               gotpar=0
               for (j=1; j<=nparlist; j++) {
                  if (IPARLIST[j]==etapar) gotpar=1
               }
               if (!gotpar) { 
                  IPARLIST[++nparlist]=etapar
#                  ppvparlist=ppvparlist substr(etapar,5) ","
                  ppvparlist=ppvparlist etapar ","
                  pat_declare=pat_declare etapar " "
               }
            }
            #OMEGA : { parameters=[ETA_CL, ETA_V], value = [0.01], type is corr }  From usecase 1
            #OMEGA1 : {type is corr, parameter=[ETA_CL, ETA_V, ETA_KA], value=[0.01, 0.01, 0.01]} From IOG product 4 discussions doc Apr 16
            printcorr_rec(matnum,etacorrtype,ppvparlist,etavallist)

         }
         if (hascorreps) {
            nepsparlist=split(substr(epsparlist,1,length(epsparlist)-1),EPSPARLIST,",")
            if (!nepsparlist) return
            ruvparlist="parameter=["; nparlist=0
            for (i=1; i<=nepsparlist; i++) {
               epspar=EPSPARLIST[i]
               gotpar=0
               for (j=1; j<=nparlist; j++) {
                  if (IPARLIST[j]==epspar) gotpar=1
               }
               if (!gotpar) { 
                  IPARLIST[++nparlist]=epspar
#                  ruvparlist=ruvparlist substr(epspar,5) ","
                  ruvparlist=ruvparlist epspar ","
                  pat_declare=pat_declare " " epspar
               }
            }
            printcorr_rec(matnum,epscorrtype,ruvparlist,epsvallist)
         }

}

function printcorr_rec(matnum,corrtype,parlist,vallist) {
   parm_obj[++nparmobj]="      MATRIX_" matnum listconnect liststart "\n\t\ttype is "corrtype",\n\t\t"substr(parlist,1,length(parlist)-1)"],\n\t\t"substr(vallist,1,length(vallist)-1)"]\n      " listend
}

function printparms(level,       wasblock,lastnomblk,lastnomega,i,noffdiag) {

      if (nomega) {
         if (isnmtran) {
            if (level==ETALEVEL) parm_obj[++nparmobj]="      #OMEGA"
            if (level==EPSLEVEL) parm_obj[++nparmobj]="      #SIGMA"
         }
         wasblock=0 ; lastnomblk=0; lastnomega=-1
         for (i=1; i<=nomega; i++) {
#print "LEVEL: OMEGA["i"]="OMEGA[i]" LEVEL["i"]="LEVEL[i]

            if (LEVEL[i]==level) {
               # Finish off block
               if (lastnomblk>0 && NOMBLK[i] != lastnomblk ) finishblock(1)
#print "OMCOV="OMCOV[NOMBLK[i]]"," OMBLKSAME[i]","NOMBLK[i]","OMCOV[OMBLKSAME[i]]
               # Start block
               if ((NOMBLK[i] || OMBLKSAME[i]) ) {
                  if (!wasblock || (NOMBLK[i] != lastnomblk) ) {
                     if (OMBLKSAME[i]) {
                        blocktype=PAR_SAME
                        blockname=PAR_STRUC OMBLKSAME[i]
                        blockattributes=blocktype"(name=\""blockname"\","
                     } else {
                        if (OMCOV[NOMBLK[i]]) {
                           blocktype=PAR_MATRIX 
                           matrixnum=toupper(blocktype)"_"++covmatnum "="
                           if (covmatnum) COVMATLIST[level,covmatnum]=covmatlist[level]
                           covmatlist[level]=""
                        } else {
                           blocktype=PAR_DIAG
                           matrixnum=""
                        }
                        blockname="\"" PAR_STRUC NOMBLK[i] "\","
                        if (OMBLKSTD[NOMBLK[i]])
                           blockvartype=TYPESD
                        else
                           blockvartype=TYPEVAR
#parm_obj[++nparmobj]= "printparms: OMBLKSTD[NOMBLK["i"]]="OMBLKSTD[NOMBLK[i]]",blockvartype="blockvartype
                        blockattributes=matrixnum blocktype "("blockname "type is "blockvartype","
                     }
                     if ((OMBLKFIX[NOMBLK[i]])) {
                        blockattributes=blockattributes"fix=true"
#parm_obj[++nparmobj]= "printparms: OMBLKFIX[NOMBLK["i"]]="OMBLKFIX[NOMBLK[i]]",blockattributes="blockattributes

                     }
                     sub(/ *, *$/,"",blockattributes)
                     if (usecovmatrix ) {
                        parm_obj[++nparmobj]="      "blockattributes") {"
                     } else {
                        #parm_obj[++nparmobj]="      ### "blockattributes")"
                        nblockoffset=i-1
                        nblockrow=0
                     }

                  }
                  wasblock=1
               }
               lastnomblk=NOMBLK[i]
               $0=OMVAL[i]
               if (OMVAL[i]==toupper(PAR_SAME))
                  $0=OMEGA[i]
               else if (OMBLK[i]) {
                  gsub(/,/," ")
                  $NF=OMEGA[i]"="$NF
               }
               gsub(/  /,"")
               gsub(/ /,", ")
               if (usecovmatrix && OMBLK[i]) {
                  parm_obj[++nparmobj]="         "$0 ","  OMCOM[i]
               } else {
                  # not usecovmatrix
                  if (OMBLK[i] ) {
                     nblockrow++
                     #if (OMVAL[i]!="SAME") covmatlist[level]=covmatlist[level] OMEGA[i] "_"
                  }
#                  if ((LEVEL[i]==level && OMFIX[i])) # changed 30 Sep 2014
                  if (OMFIX[i] || OMBLKFIX[NOMBLK[i]])
                     omfixtext=", fix=true"
                  else
                     omfixtext=""
                  if (OMSTD[i] || OMBLKSTD[NOMBLK[i]] )
                     omvartext=", type is "TYPESD
                  else
                     omvartext=", type is "TYPEVAR

# Look for ", PPV_" etc. if found then this means this is a block with offdiagonal elements
                  if (LEVEL[i]==level) {
                     if (level==ETALEVEL)
                        varchars="PPV_"
                     else
                        varchars="RUV_"
                     if (index($0,", "varchars)) {
                        hasvarchars=1
                     } else {
                        varchars="BOV_"
                        if (index($0,", "varchars)) {
                           hasvarchars=1
                        } else {
                           varchars="BSV_"
                           if (index($0,", "varchars)) {
                              hasvarchars=1
                           } else {
                              varchars="BTV_"
                              if (index($0,", "varchars)) {
                                 hasvarchars=1
                              } else {
                                 hasvarchars=0
                              }
                           }
                       }
                     }
#if (OMVAL[i]!="SAME") print "printparms: hasvarchars="hasvarchars " OMVAL["i"]="OMVAL[i]" OMEGA["i"]="OMEGA[i] " $0="$0
#if (OMVAL[i]!="SAME") print "printparms: hasvarchars="hasvarchars " $0="$0
                     if (!hasvarchars) {
#parm_obj[++nparmobj]="printparms: OMBLKSAME["i"]="OMBLKSAME[i] " hasocc=|"hasocc"|"
                        # Don't print SAME parameters
                        if (hasocc==0 || OMBLKSAME[i]+0==0) parm_obj[++nparmobj]="      "OMEGA[i] listconnect listname liststart "value="simplify(OMVAL[i]) omvartext omfixtext OMUNITS[i] listend OMCOM[i]
                     } else {
#print "printparms: usecovmatrix="usecovmatrix " hasvarchars="hasvarchars" varchars="varchars" level="level" ETALEVEL="ETALEVEL" $0="$0
                        noffdiag=split(OMVAL[i],OFFDIAG,",")
#if (OMVAL[i]!="SAME") parm_obj[++nparmobj]= "printparms: OMVAL["i"]="OMVAL[i]" noffdiag="noffdiag
#if (OMVAL[i]!="SAME") print "printparms: OMVAL["i"]="OMVAL[i]" OMEGA["i"]="OMEGA[i] " $"NF"="$NF
                        parm_obj[++nparmobj]="      "OMEGA[i] listconnect listname liststart "value="simplify(OFFDIAG[noffdiag]) omvartext omfixtext OMUNITS[i] listend OMCOM[i]
                        #CORR_PPV_CL_V : {hi=1.0, lo=-1.0, type=CORR, value=0.2345}
                        offdiag=$0
                        gsub(/ /,"",offdiag)
                        ncovname=split(offdiag,OFFDIAG,",")
                        if (index(omvartext,"type is "TYPESD))
                           corrtype[level]="corr"
                        else
                           corrtype[level]="cov"
                        if (level==ETALEVEL)
                           ranchars="eta_"
                        else if (level==EPSLEVEL)
                           ranchars="eps_"
                        else
                           ranchars="unknownlevel_"
                        for (k=1;k<=ncovname;k++) {
                           offdiag=OFFDIAG[k]+0
                           if (1==1 || offdiag) {
                              covnamek=OMEGA[k+nblockoffset] #; gsub(/PPV_/,"",covnamek); gsub(/RUV_/,"",covnamek); gsub(/BOV_/,"",covnamek)
                              for (kk=nblockrow;kk<=ncovname;kk++) {
                                 if (k!=kk) {
                                    covnamekk=OMEGA[kk+nblockoffset] #; gsub(/PPV_/,"",covnamekk); gsub(/RUV_/,"",covnamekk); gsub(/BOV_/,"",covnamekk)
                                    CORRLIST[++ncorrlist]="corr_"covnamek"_"covnamekk
                                    CORR_RVS[ncorrlist,1]=ranchars covnamek; CORR_RVS[ncorrlist,2]=ranchars covnamekk
                                    CORRTYPE[ncorrlist]=corrtype[level]; CORRVALUE[ncorrlist]=offdiag; COVMATNUM[ncorrlist]=covmatnum
#                                    parm_obj[++nparmobj]="      "CORRLIST[ncorrlist]": {hi=1.0, lo=-1.0, type="corrtype[level]", value="offdiag"}"
#print "printparms: CORRLIST["ncorrlist"]="CORRLIST[ncorrlist]

                                 }
                              }
                           }
                        }
                     }
                  } # if level
               }
               if (uscovmatrix ) {
                  covmatlist[level]=covmatlist[level] OMEGA[i] ", "
               }
               lastnomega=i # i is index of nomega
            } # end of level block
            if (covmatnum) {
               COVMATLIST[level,covmatnum]=covmatlist[level]
               if (!usecovmatrix) {
                  COVMATTYPE[level,covmatnum]=corrtype[level]
               }
            }
         } # end of nomega loop
#print "printparms: finish LEVEL["lastnomega"]="LEVEL[lastnomega]
         if (LEVEL[lastnomega]==level) {
            # Finish off last block
#print "printparms: finish OMBLK["lastnomega"]="OMBLK[lastnomega]
            if (OMBLK[lastnomega]) {
#print "OMEGA["lastnomega"]="OMEGA[lastnomega]" level="level " NOMBLK["lastnomega"]="NOMBLK[lastnomega]" OMBLKFIX[NOMBLK["lastnomega"]]="(OMBLKFIX[NOMBLK[lastnomega]])
              finishblock(1)
            }
         } # lastnomega for this level

      }
}

function simplify(val) {
   if (index(val,"(") && !index(val,",")) {
      sub(/\(/,"",val)
      sub(/\)/,"",val)
   }
   sub(/ *$/,"",val)
   return val
}
function finishblock(addtype) {
   sub(/ *, *$/,"",parm_obj[nparmobj]) # remove trailing comma
   sub(/ *, *\#/," #",parm_obj[nparmobj]) # remove trailing comma
   if (usecovmatrix && addtype) parm_obj[++nparmobj]="      } # end "blocktype" "blockname
}

function amt_reference(   j,saveamt,repamt,savelhs,lhs,ieq) {
   if (!hasamount || index($1,"#")==1) return
   sub(/\=/," = ")
   for (j=1; j<=ncmt; j++) {
      if (advan_num==5 || advan_num==7) CMT[j]="A["j"]"
      if (CMT[j]=="") {
         CMT[j]="A["j"]"
         isacmt=1
      } else
         isacmt=0
#print "CMT["j"]="CMT[j]
      if (!CMLHS[j]) {
         if ($1==CMT[j]) CMLHS[j]=1
         savelhs=$0
         ieq=index(savelhs,"=")
         lhs=substr(savelhs,1,ieq)
         $0=substr(savelhs,ieq+1)
         saveamt=$0
         replace(CMT[j],"A("j")")
         repamt=$0
         $0=saveamt
         if (isacmt) {
            gsub(CMT[j],"A["j"]")
            replace("A["j"]","A("j")")
         } else {
            replace(CMT[j],"A("j")")
         }
         $0=lhs $0
      }
   }
   sub(/ \= /,"=")
}

function model_object(   i,j,model_idv) {
   nmodelobj=0
   model_obj[++nmodelobj]=mdlname"_mdl = "MDL_OBJ objstart
   if (ishelp) {
      model_obj[++nmodelobj]="### Model object"
      if (usemcl5obj) {
         model_obj[++nmodelobj]="# The model object receives a list of input data variables from the data object and parameters (constants)from the parameter object."
         model_obj[++nmodelobj]="# The parameter and variable names in the model object must be matched by a similar name in the parameter and data objects." 
         model_obj[++nmodelobj]="# Note that variables in the data object and parameters may be defined in the parameter object that are not referenced in the model object."
      } else {
         model_obj[++nmodelobj]="# The model input variables are partly described in the model object and partly described in the data object."
      }
      model_obj[++nmodelobj]="# Model variables are derived from the input data variables and parameters using algebraic expressions."
   }
   model_obj[++nmodelobj]=""
   if (!usedataidv) {
      if (ndeqline)
         model_idv="T"
      else
         model_idv=idvname

      model_obj[++nmodelobj]="   "MDL_IDV blockstart model_idv blockend
   }

   if (ninput) {
      model_obj[++nmodelobj]="   "MDL_INPUT blockstart""
      model_location[nmodelobj]=""
      if (ishelp) {
         if (usemcl5obj) {
            model_obj[++nmodelobj]="# A name for each input data variable is required. Special uses of input data variables are indicated by the use attribute if the data variable name is not a standard name. Standard names may include ID, IDV, AMT, DV, MDV, etc. similar to standard data items recognized by NM-TRAN or Monolix. The type option is not required but may be useful as metadata. This kind of metadata (with an option to recode) could alternatively be specified in the data object."
         } else {
            model_obj[++nmodelobj]="# Model input variables with use is covariate."
         }
      }
      gsub(/=/," ",inputline)
      if (ninput) {
         mdlvarcovariates=1; mdlvarlevels=0
         mdlvarcode()
         for (i=1; i<=nmclcovlist; i++) {
            model_obj[++nmodelobj]=MCLCOVLIST[i]
         }
      }
      if (showblockend) model_obj[++nmodelobj]="   "blockend"# end "MDL_INPUT
      if (ninput && !usemcl5obj) {
         model_obj[++nmodelobj]=""
         model_obj[++nmodelobj]="   "MDL_LEVELS blockstart""
         mdlvarcovariates=0; mdlvarlevels=1
         mdlvarcode()
         if (showblockend) model_obj[++nmodelobj]="   "blockend"# end "MDL_LEVELS
      }
      model_obj[++nmodelobj]=""

   }

   if (ntheta || nomega || nsigma) {
      if (ntheta) {
         if (ishelp) model_obj[++nmodelobj]="# The "MDL_STRUCPAR" Block defines the structural model parameters."
         model_obj[++nmodelobj]="   "MDL_STRUCPAR blockstart""
         if (ishelp) {
            model_obj[++nmodelobj]="# A name for each parameter is required. Other attributes such as units are optional."
            model_obj[++nmodelobj]="# Units may be used to achieve consistency between the parameter, data and model object values."
            model_obj[++nmodelobj]="# This could be implemented during the WP2 translation process."
            model_obj[++nmodelobj]=""
         }
         if (isnmtran) model_obj[++nmodelobj]="      #THETA"

	      gotlocation=0
         for (i=1; i<=ntheta; i++) {
#print "model  object: THUNITS["i"]="THUNITS[i]
            if (index(THUNITS[i],", units")) {
               sub(/, /,"",THUNITS[i])
#print "model  object: listconnect="listconnect
               THUNITS[i]= listconnect listname liststart THUNITS[i] listend
            }
            model_obj[++nmodelobj]="      "THETA[i] THUNITS[i]
            if (!gotlocation) { model_location[nmodelobj]="$THETA"; gotlocation=1}
         }
         if (showblockend) model_obj[++nmodelobj]="   "blockend"# end "MDL_STRUCPAR
      }
      if (nomega || nsigma) {
         model_obj[++nmodelobj]=""
         model_obj[++nmodelobj]="   "MDL_VARPAR blockstart""
         if (ishelp) model_obj[++nmodelobj]="# The "MDL_VARPAR" Block defines the variability parameters."
      }

      if (nomega) {
         if (isnmtran) model_obj[++nmodelobj]="      #OMEGA"
         gotlocation=0
         for (i=1; i<=nomega; i++) {
#model_obj[++nmodelobj]= "MDL_VAR: OMEGA["i"]="OMEGA[i]" LEVEL["i"]="LEVEL[i]
               if (LEVEL[i]==ETALEVEL) {
                  # Don't print SAME parameters
                  if (hasocc==0 || OMBLKSAME[i]+0==0) model_obj[++nmodelobj]="      "OMEGA[i]            
                  if (!gotlocation) { model_location[nmodelobj]="$OMEGA"; gotlocation=1}
               }
         }
      }

      if (nsigma || !hasid) {  # If there is no ID then OMEGA is used as Level 1
         if (isnmtran) model_obj[++nmodelobj]="      #SIGMA"
         gotlocation=0
         for (i=1; i<=nomega; i++) {
               if (LEVEL[i]==EPSLEVEL) {
                  if (index(OMUNITS[i],", units")) {
                     sub(/, /,"",OMUNITS[i])
                     OMUNITS[i]= listconnect listname liststart OMUNITS[i] listend
                  }
                  # Don't print SAME parameters
                  if (OMBLKSAME[i]+0==0) model_obj[++nmodelobj]="      "OMEGA[i] OMUNITS[i]            
                  if (!gotlocation) { model_location[nmodelobj]="$SIGMA"; gotlocation=1}
               }
         }
      }
      if (nomega || nsigma) {
         if (showblockend) model_obj[++nmodelobj]="   "blockend"# end "MDL_VARPAR
      }
      model_obj[++nmodelobj]=""
   }

   #find estimation options to see if LIKELIHOOD is requested
   estoptions()
   
   # Find model prediction code
   ifcount=0; nlikline=0; hasy=0
   if (nerrline==0) { # $PRED
       findpred(pred_code,npredline)
   }

  ngrpline=0
  if (npredline) {
      firstindline=0
      for (i=1; i<=npredline; i++) {
         $0=pred_code[i]
         if ($0!="#DROP") {

            isgroup=(!findpat("ETA"))
            if (index($0,MDLIND)) isgroup=0 # pragma
            if (match($0,/^ *; *@MCL_ETAPPV/)) isgroup=0 # pragma
#model_obj[++nmodelobj]= "model_object nerrline="nerrline" npredline="npredline" pred_code["i"]="$0" isgroup="isgroup
            if (isgroup) {
               grp_code[++ngrpline]=$0
               if (index($1,"IF")==1 && index($0,"THEN")) {
                  firstindline=i
                  lastgrpline=i-1
               } else if (firstindline && $1=="ENDIF") { #(!(index($1,";")==1 || $NF>0) || !(index($1,"IF")==1 && index($0,"THEN")) )) {
                  firstindline=0
                  lastgrpline=0
               }
            } else {
               $0=grp_code[ngrpline]
               nindline=i
               if (firstindline) {
                  nindline=firstindline
                  ngrpline=lastgrpline
               }
               i=npredline+1
            }
         }
      }

# Group variables only written if there are also individual variables
      if (ngrpline==1 && grp_code[1]=="") ngrpline=0
      if (ngrpline && nindline) {
         # Group code
         model_obj[++nmodelobj]="   "MDL_GRPPAR blockstart
         model_location[nmodelobj]=pred_location; gotlocation=1
         if (ishelp) {
            model_obj[++nmodelobj]="# The "MDL_GRPPAR" block is used to express covariate models i.e. the fixed effect differences in parameters predictable from data variables."
            model_obj[++nmodelobj]="# If there are no covariate effects to model then this block is not required."
            model_obj[++nmodelobj]="# NONMEM may be able to take advantage of knowing that variables defined in this block represent the central tendency"
            model_obj[++nmodelobj]="# of the distribution of random effects defined in the "MDL_INDPAR" block (known as MU referencing)."
            model_obj[++nmodelobj]="# In this example the prefix \"grp_\" for a variable name could be a hint that this is a MU variable for NM-TRAN."
            model_obj[++nmodelobj]="# Other model variables may be defined in this block according to user preference."
            model_obj[++nmodelobj]=""
         }
         for (i=1; i<=ngrpline; i++) {
            $0=grp_code[i]
            if (!(i==1 && $0=="")) {
               if (translate()) {
                  model_obj[++nmodelobj]="   "$0
               }
            }
         }

         if (nmixline) mixblock()

         if (showblockend) model_obj[++nmodelobj]="   "blockend"# end "MDL_GRPPAR
         model_obj[++nmodelobj]=""
      }
      # Individual code
      hasFpred=0
      if (!isgroup) {
         for ( j=1; j<=nomega ; j++ ) SAMEBLOCK[j]==0
         for ( j=1; j<=nomega ; j++ ) {
            if (OMBLKSAME[j]+0) {
               for (k=1; k<=nomega; k++) {
                   if (NOMBLK[k]+0==OMBLKSAME[j]+0) {
                      SAMEBLOCK[k]=OMBLKSAME[j]
                   }
               }
               SAMEBLOCK[j]=OMBLKSAME[j]
#model_obj[++nmodelobj]="model_object: NOMBLK[j]="NOMBLK[j]" OMEGA[j]="OMEGA[j] " OMBLKSAME["j"]="OMBLKSAME[j]
            }
         }
        
        if (nomega || nsigma) {
            model_obj[++nmodelobj]=""
            for ( j=1; j <= nomega ; j++ ) {
               if (LEVEL[j]==ETALEVEL) {
                  leveltype="DV"
                  for (k=1; k<=ninput; k++) {
                        if (toupper(INPUT[k])=="ID") leveltype="ID"
                  }
                  prefix="eta_"
               } else {
                  prefix="eps_"
               }
               if (OMSTD[j] || OMBLKSTD[NOMBLK[j]] )
                  omvartext=TYPESD
               else
                  omvartext=TYPEVAR
               if (SAMEBLOCK[j]) {
                  rvdrec="      " prefix OMEGA[j] listdistconnect NORMDIST"(mean=0, "omvartext"="OMEGA[j]")" # , level="leveltype
               } else {
                  rvdrec="      " prefix OMEGA[j] listdistconnect NORMDIST"(mean=0, "omvartext"="OMEGA[j]")" # , level="leveltype
               }
               if (LEVEL[j]==ETALEVEL)
                  RVD[ETALEVEL,j]=rvdrec
               else 
                  RVD[EPSLEVEL,j]=rvdrec
               lastomega=thisomega
            }

            if (covmatnum) {

               if (usecovmatrix) {
                  if (1==0) {
                     # Define correlation list and link to parameter object matrix name
                     for (j=1; j<=covmatnum; j++) {
                        level=ETALEVEL
                        covmat=COVMATLIST[level,j]
                        ranchars="eta_"
                        varchars="PPV_"
                        if (!index(covmat,varchars)) {
                           varchars="BOV_"
                           covmat=COVMATLIST[level,j]
                        }
                        if (covmat=="") {
                           level=EPSLEVEL
                           covmat=COVMATLIST[level,j]
                           ranchars="eps_"
                           varchars="RUV_"
                        }
                        if (covmat!="") {
                           sub(/, $/,"",covmat)
                           RVDCORR[level,j]="      correlation" liststart covmat listend "=" "MATRIX_"j
                        }
                     }
                  } # covmatnum loop
               } else if (1==0) { # not usecovmatrix for Product 1 to 3 only
                  hascorreps=0
                  for (j=1; j<=ncorrlist; j++) {
                     corrlistname=CORRLIST[j]
                     covnamek=CORR_RVS[j,1]
                     covnamekk=CORR_RVS[j,2]
                     if (index(covnamek,"eta_")==1)
                        #CORR_PPV_CL_V : {level=ID, rv1=ETA_CL, rv2=ETA_V, type=CORR}
                        RVDCORR[ETALEVEL,j]="      "corrlistname": {rv1="covnamek", rv2="covnamekk", type is "CORRTYPE[j] listend # ", level=ID}"
                     else {
                        RVDCORR[EPSLEVEL,j]="      "corrlistname": {rv1="covnamek", rv2="covnamekk", type is "CORRTYPE[j] listend # ", level=DV}"
                        hascorreps=1
                     }
                  }
               }
            }

            if (ishelp) model_obj[++nmodelobj]="# The "MDL_RVD" block is used to define the distribution of random variables."

            hasrvd=0
            for (j=1; j<=nomega; j++) {
               if (hasocc==0 || (RVD[ETALEVEL,j]!="" && !SAMEBLOCK[j]+0)) hasrvd=1
            }
            if (hasrvd) {
               model_obj[++nmodelobj]="   "MDL_RVD " (level=ID) " blockstart
               for (j=1; j<=nomega; j++) {
                   # Don't print SAME parameters
                   if (hasocc==0 || (RVD[ETALEVEL,j]!="" && !SAMEBLOCK[j]+0)) model_obj[++nmodelobj]=RVD[ETALEVEL,j]
               }

# Product 1 to 3 only
#            model_obj[++nmodelobj]="      #Random variable correlations"
#            for (j=1; j<=ncorrlist; j++) {
#                if (RVDCORR[ETALEVEL,j]!="") model_obj[++nmodelobj]=RVDCORR[ETALEVEL,j]
#            }

               if (showblockend) model_obj[++nmodelobj]="   "blockend"# end "MDL_RVD " (level=ID)"
               model_obj[++nmodelobj]=""
            }
            hasrvd=0
            for (j=1; j<=nomega; j++) {
               if (RVD[ETALEVEL,j]!="" && SAMEBLOCK[j] && OMBLKSAME[j]+0==0) hasrvd=1
            }
            if (hasocc && hasrvd) {
               model_obj[++nmodelobj]="   "MDL_RVD " (level=OCC) " blockstart
               for (j=1; j<=nomega; j++) {
                   # Use SAME parameters to identify RVD occasion level
                   if (RVD[ETALEVEL,j]!="" && SAMEBLOCK[j] && OMBLKSAME[j]+0==0) {
                      model_obj[++nmodelobj]=RVD[ETALEVEL,j]
                   }
               }
               if (showblockend) model_obj[++nmodelobj]="   "blockend"# end "MDL_RVD " (level=OCC)"
               model_obj[++nmodelobj]=""
            }

            hasrvd=0
            for (j=1; j<=nomega; j++) {
               if (RVD[EPSLEVEL,j]!="" && (OMBLKSAME[j]+0==0)) hasrvd=1
            }
            if (hasrvd) {
               model_obj[++nmodelobj]="   "MDL_RVD " (level=DV) " blockstart
               for (j=1; j<=nomega; j++) {
                   # Don't print SAME parameters
                   if (RVD[EPSLEVEL,j]!="" && (OMBLKSAME[j]+0==0)) model_obj[++nmodelobj]=RVD[EPSLEVEL,j]
               }

# Product 1 to 3 only
#            if (hascorreps) {
#               model_obj[++nmodelobj]="      #Random variable correlations"
#               for (j=1; j<=ncorrlist; j++) {
#                   if (RVDCORR[EPSLEVEL,j]!="") model_obj[++nmodelobj]=RVDCORR[EPSLEVEL,j]
#               }
#            }
               if (showblockend) model_obj[++nmodelobj]="   "blockend"# end "MDL_RVD " (level=DV)"
               model_obj[++nmodelobj]=""
            }
         }
         
         if (index(pred_code[nindline],MDLIND)) ++nindline # pragma
         if (nindline<=npredline) {
            model_obj[++nmodelobj]="   "MDL_INDPAR blockstart""
            if (ishelp) {
               model_obj[++nmodelobj]="# The "MDL_INDPAR" block is used to express the individual variables and their distribution."
               model_obj[++nmodelobj]="# If this is not a population model then this block is not required."
               model_obj[++nmodelobj]=""
            }
            isind=1
            for (i=nindline; i<=npredline; i++) {
               $0=pred_code[i]
               ismclverbatim=match($0,/^ *; *@MCL_\"/)
               if (ismclverbatim) {
                 sub(/^ *; *@MCL_\"/,"")
                 verbatimrec=$0
               }
               if ($0!="#DROP") {
                  if (isind) {
                     isind=!findpat("EPS")
                     if (isind) isind=!findpat("ERR")
                  }
                  if (isind) {
                     if (ismclverbatim) {
                        $0=verbatimrec
                     } else {
                        if (j=index($0,";"))
                           code=substr($0,1,j-1)
                        else
                           code=$0
                        if (match(code,/= *F *$/)) hasFpred=1
                        if (translate()) {
                           for (j=1; j<=nomega; j++) {
                              if (SAMEBLOCK[j]) {
                                 gsub(/ *\+ */,"+")
                                 pat="+"OMEGA[j]
                                 sub(pat,"+eta_"OMEGA[j])
                              }
                           }
                        }
                     }
                     model_obj[++nmodelobj]="   "$0
                  } else {
                     err_code[++nerrline]=$0
                  }
               }
            }
            hasazero=0
            for (i=1; i<=ncmt; i++) {
      #model_obj[++nmodelobj]="AZERO: "AZERO[i]
               if (index(AZERO[i],"A_0(")) {
                  hasazero=1
                  i=ncmt+1
               }
            }
            if (hasazero) {
               for (i=1; i<=ncmt; i++) {
                  $0=AZERO[i]
                  for (j=1; j<=ncmt; j++) {
                     replace("init_"j,"A_0("j")")
                  }
                  model_obj[++nmodelobj]="      init_"i"="$0
                  AZERO[i]="init_"i
               }
            }
            if (!ndeqline && nazero) {
                for (i=1; i<=nazero; i++)
                  model_obj[++nmodelobj]="   A["i"]="AZERO[i]
                  
            }
            model_obj[++nmodelobj]=""

            if (showblockend) model_obj[++nmodelobj]="   "blockend"# end "MDL_INDPAR
            model_obj[++nmodelobj]=""
         } # nindline
      } # !isgroup

   } # npredline
   
   # Find model prediction code for LIKE
   findpred(err_code,nerrline)

   makeparlist()
   
   if (par_list!="") {
      out_list=""
      if (!(advan_num==5 || advan_num==7)) {
         for (i=1; i<=ncmt; i++) {
            out_list=out_list","CMT[i]
         }
      }
      sub(/,$/,"",out_list)
      out_list="F,A" out_list
   } else
      out_list=""
   model_obj[++nmodelobj]="   "MDL_PRED blockstart""
   if (ishelp)  {
      model_obj[++nmodelobj]="# The "MDL_PRED" block calculates model predictions based on model variables defined in the previous model object blocks."
      model_obj[++nmodelobj]="# Code in this block expresses the structural model after group and individual parameter differences have been included."
      model_obj[++nmodelobj]="# It can be considered as an expression of a deterministic prediction. It is the essential component of a shared sub-model."
      model_obj[++nmodelobj]="# This block should not contain any covariate computations expressing predictable or unpredictable (random) between subject differences."
      model_obj[++nmodelobj]=""
 #     model_obj[++nmodelobj]="# List of variables used for prediction that will be used as parameters if this model is called from a model library."
   }
 #  model_obj[++nmodelobj]="      param" listconnect listname liststart predargs listend

   model_location[nmodelobj]="$PRED"
   if (1==0 && nmodline) {
      if (isnmtran) {
         model_obj[++nmodelobj]=""
         model_obj[++nmodelobj]="      ## COMPARTMENTS"
         for (i=1; i<=nmodline; i++) {
            $0=mod_code[i]
            if (translate()) model_obj[++nmodelobj]="      ## "$0
         }
      }
   }

   if (nsubline && !ndeqline) {
      if (nliblist || advan_num) { # library call
         nocmtequiv=(advan_num==6 || advan_num==8 || advan_num==9 || advan_num==13 || tran_num==3 || tran_num==6)
         showlib=(!(iscmtido || iscompartment) || nocmtequiv)
         if (showlib)
            mdl_block=MDL_LIB
         else if (iscmtido)
            mdl_block=MDL_IDO
         else
            mdl_block=MDL_CMT


         model_obj[++nmodelobj]=""
         model_obj[++nmodelobj]="   "mdl_block blockstart
         if (advan_num) {
            if (isnmtran) {
               gotlocation=0
               for (i=1; i<=nsubline; i++) {
                  $0=sub_code[i]
                  if (translate()) {
                     model_obj[++nmodelobj]="      ## "$0
                     if (!gotlocation) { model_location[nmodelobj]="$SUBROUTINE"; gotlocation=1 }
                  }
               }
            }
            if (showlib) {
               nlibsubblock=0
               library_subblock()
               for (i=1; i<=nlibsubblock; i++) {
                  model_obj[++nmodelobj]=lib_subblock[i]
               }
            } else if (iscompartment) {
               ncmtsubblock=0
               compartment_subblock()
               for (i=1; i<=ncmtsubblock; i++) {
                  model_obj[++nmodelobj]=cmt_subblock[i]
               }
            } else if (iscmtido) {
               nidosubblock=0
               cmt_ido_subblock()
               for (i=1; i<=nidosubblock; i++) {
                  model_obj[++nmodelobj]=cmt_subblock[i]
               }
            } else if (!ismdlpred) {
               print "nt2mdl error: not iscmtido and not iscompartment"
            }

            # Allow replacement of CMT name with amount.CMT name
            for (i=1; i<=ncmt; i++) CMLHS[i]=0
            hasamount=1
         }

      # Show alternative commented out form as LIBRARY or COMPARTMENT
      # Only if there is both a LIBRARY and COMPARTMENT equivalent
         if (!nocmtequiv) {
            if (showlib) {
               ncmtsubblock=0
               cmt_ido_subblock()
               for (i=1; i<=nidosubblock; i++) {
                  model_obj[++nmodelobj]=cmt_subblock[i]
               }
               ncmtsubblock=0
               compartment_subblock()
               for (i=1; i<=ncmtsubblock; i++) {
                  model_obj[++nmodelobj]=cmt_subblock[i]
               }
            } else {
               nlibsubblock=0
               library_subblock()
               for (i=1; i<=nlibsubblock; i++) {
                  model_obj[++nmodelobj]=lib_subblock[i]
               }
               showlib=1
               ncmtsubblock=0; nidosubblock=0
               if (iscompartment) {
                  cmt_ido_subblock()
                  ncmtsubblock=nidosubblock
               } else
                  compartment_subblock()
               for (i=1; i<=ncmtsubblock; i++) {
                  model_obj[++nmodelobj]=cmt_subblock[i]
               }
               showlib=0
            }
         }
      } # library call
   } # nsubline

###  DEQ sub-block

   if (ndeqline) deq_subblock()

   if (!nindline) {
      for (i=1; i<=ngrpline; i++) {
         $0=grp_code[i]
         if ($0!="#DROP") {
            if (translate()) model_obj[++nmodelobj]="   "$0
         }
      }
   }

   
   if (nerrline) {
      gotlocation=0
      for (i=1; i<=nerrline; i++) {
         $0=err_code[i]
#print "nerrline: $0="$0
         if (match($0,/^ *; *@MCL_\"/)) {
#print "nerrline: $0="$0
            for (j=1; j<=ndvid; j++) {
#print "nerrline: $2="$2 " YDVID["j"]="YDVID[j] 

               if ($2==YDVID[j] || $2=="ln("YDVID[j]")") {
                  sub(/^ *; *@MCL_\"/,"")
                  OBSBLOCK[++nobsblock]=$0
#print "nerrline: OBSBLOCK["nobsblock"]="OBSBLOCK[nobsblock]
                  $0=""
               }
            }
         }

#model_obj[++nmodelobj]="nerrline["i"]="$0 
         if ($0!="#DROP") {
            if (translate()) {
               # express the return value from the library function
               amt_reference()
               model_obj[++nmodelobj]="   "$0
               if (!gotlocation) {
                  if (haserror) pred_location="$ERROR"
                  model_location[nmodelobj]=pred_location
   #print "gotlocation="gotlocation" model_location["nmodelobj"]="model_location[nmodelobj]" "$0
                  gotlocation=1 
               }
            }
         }
      }
   }
   # Not sure if this is the right place for TARGET_CODE with PRIOR??
   if (npriorline) { model_location[nmodelobj]="$PRIOR"; gotlocation=1 }

   if (showblockend) model_obj[++nmodelobj]="   "blockend"# end "MDL_PRED
   model_obj[++nmodelobj]=""

#model_obj[++nmodelobj]= "Model Obs: nobsblock="nobsblock

  if (nobsblock) {
         model_obj[++nmodelobj]="   "MDL_OBS blockstart
         for (i=1; i<=nobsblock; i++) {
#print "nobsblock="i" OBSBLOCK["i"]="OBSBLOCK[i]
            model_obj[++nmodelobj]=OBSBLOCK[i]
         }
         if (showblockend) model_obj[++nmodelobj]="   "blockend"# end "MDL_OBS
         model_obj[++nmodelobj]=""
  } else {

      addobj=0
      observation_block()
      if (estrectype==TYPECONTINUOUS || nsimline) {
         if (estrectype=="") {
            if (nomega==0 && nsigma==0)
               blockname=MDL_SIM
            else
               blockname=MDL_EST
         } else
            blockname=MDL_OBS
         model_obj[++nmodelobj]="   "blockname blockstart
         gotlocation=0
         addobj=1
         #This is a workaround to create an OBSERVATION block so that 
         # NM-TRAN will have a Y=expression statement when there is no $ESTIMATION TARGET_CODE block 
         oldesttype=estrectype
         if (nsimline && estrectype=="") estrectype=TYPECONTINUOUS
         observation_block()
         estrectype=oldesttype
         if (showblockend) model_obj[++nmodelobj]="   "blockend"# end "blockname
         model_obj[++nmodelobj]=""
      }
      if (nestline && !(estrectype==TYPECONTINUOUS)) { # && !nsimline)) {
         model_obj[++nmodelobj]="   "MDL_EST blockstart
         if (ishelp) {
            model_obj[++nmodelobj]="# The "MDL_EST" block is used when the model is used for parameter estimation." 
            model_obj[++nmodelobj]="# Whether estimation or simulation is performed is determined by the task which uses the model."
            model_obj[++nmodelobj]="# The likelihood for a particular DV is calculated e.g. from the model prediction of the concentration and a residual error model." 
            model_obj[++nmodelobj]="# There may be more than one type of prediction which may be selected depending on an input variable such as DVID."
            model_obj[++nmodelobj]=""
         }
#model_obj[++nmodelobj]= "Model Est: nobsblock="nobsblock
        if (nobsblock) {
               model_obj[++nmodelobj]="   "MDL_EST blockstart
               for (i=1; i<=nobsblock; i++) {
      #print "nobsblock="i" OBSBLOCK["i"]="OBSBLOCK[i]
                  model_obj[++nmodelobj]=OBSBLOCK[i]
               }
               if (showblockend) model_obj[++nmodelobj]="   "blockend"# end "MDL_EST
               model_obj[++nmodelobj]=""
        } else {
            gotlocation=0
            addobj=1
            observation_block()

            if (showblockend) model_obj[++nmodelobj]="   "blockend"# end "MDL_EST
        } # nobsblock=0

      } # nestline

      model_obj[++nmodelobj]=""
   }


   if (neq4line && !(estrectype==TYPECONTINUOUS && !nsimline)) {
      model_obj[++nmodelobj]="   "MDL_SIM blockstart
      if (ishelp) {
         model_obj[++nmodelobj]="# The "MDL_SIM" block is used to simulate values."
         model_obj[++nmodelobj]="# This may be as simple as adding a simulated residual error to a model variable prediction or it may involve more complex code"
         model_obj[++nmodelobj]="# which may modify data variables and create a new data object for a subsequent task."
         model_obj[++nmodelobj]="# Simulated variables may be used to replace variables in data set"
         model_obj[++nmodelobj]="# This should be used with care if the target is NONMEM because these"
         model_obj[++nmodelobj]="# values will overwrite those in the input data set for subsequent sub-problems"
         model_obj[++nmodelobj]="# e.g.  DV~(type=normal, mean=Y, sd=SD))"
      }
      model_obj[++nmodelobj]=""
      gotlocation=0
      for (i=1; i<=neq4line; i++) {
         $0=eq4_code[i]
         if (translate()) {
            model_obj[++nmodelobj]="   "$0
            if (!gotlocation) { model_location[nmodelobj]="$SIMULATION"; gotlocation=1}
         }
      }
      model_obj[++nmodelobj]=""
      if (showblockend) model_obj[++nmodelobj]="   "blockend"# end "MDL_SIM
      model_obj[++nmodelobj]=""
   }

   if (usemdlout && ntabline && !(istablecode)) {
      model_obj[++nmodelobj]=""
      model_obj[++nmodelobj]="   "MDL_OUTPUT blockstart
      model_location[nmodelobj]="$TABLE"
      if (ishelp) model_obj[++nmodelobj]="# The "MDL_OUTPUT" block specifies the model variables that may be exported from the model object."
      table_options=""; outputline=""
      for (i=1; i<=ntabline; i++) {
         $0=tab_code[i] " "
         j=index($0,";"); if (j) $0=substr($0,1,j-1) # remove any comment
         if (translate()) {
            for (j=1; j<=ntabopt; j++) {
               if (index($0,TABOPT[j])) {
                  sub(TABOPT[j],"")
                  table_options=table_options TABOPT[j] " "
               }
            }
            
            if (j=match($0,/FILE *=/)) {
               table_options=table_options substr($0,j)
               $0=substr($0,1,j-1)
            }

            outputline=outputline $0 #"#" comment
         }
      }

# model_obj[++nmodelobj]=outputline
      
  
      # change ETA() to ETA
      gsub(/\(/,"",outputline)
      gsub(/\)/,"",outputline)

      noutput=split(outputline,OUTPUT)
      noutitem=0
      for (i=1; i<=noutput; i++) {
         item=OUTPUT[i]
         gotitem=0
         for (j=1; j<=noutitem; j++) {
            if (item==OUTITEM[j]) {
               gotitem=1
               j=noutitem+1
            }
         }
         if (!gotitem) {
            OUTITEM[++noutitem]=item
            if (index(item,"="))
               item = "#" item
            else {
               if (match(item,/^ETA[1-9]/)) {
                  etanum=substr(item,4)+0
                  jomega=0
                  for (j=1; j<=nomega; j++) {
                     if (LEVEL[j]==ETALEVEL) {
                        jomega++
                        if (jomega==etanum) j=nomega+1
                     }
                  }
                  item="eta_"OMEGA[etanum]
               }
            }
            model_obj[++nmodelobj]="      "item
         }
      }
      if (showblockend) model_obj[++nmodelobj]="   "blockend"# end "MDL_OUTPUT
      model_obj[++nmodelobj]=""
   }
   
   model_obj[++nmodelobj]=objend" # end of model object"
   model_obj[++nmodelobj]=""

####  final pass through model object 
   isverbatim=0; hadpos=""
   hadifblock=0; nthen=0; nelse=0; maxifblock=0; nifblock=0 ;gotelse=0; isif=0; nobsblock=0; skipaline=0
   for (i=1; i<=nmodelobj; i++) {
      $0=model_obj[i]
      if (!match($0,/^ *#@NMTRAN!/)) { # skip NMTRAN 
         if (model_location[i]!="") {
            location=model_location[i]
         }
         if (location=="") location="INLINE"
         sub(/^ *# *@MCL_\"/,"")
         sub(/L\/h\/70kg/,"L/h/kg*70")
         sub(/L\/70kg/,"L/kg*70")
         if (index($1,NMTRAN_VERBATIM_CHAR)==1) {
            if (!isverbatim) {
               isverbatim=1
               if ($1==NMTRAN_VERBATIM_CHAR "FIRST") hadpos="\""location"\"" ", first=true"
               if ($1==NMTRAN_VERBATIM_CHAR "LAST") hadpos="\""location"\"" ", last=true"
               open_target(MDL_NMTRAN,"INLINE","")
            }
            if (index($0,NMTRAN_VERBATIM_CHAR OTHER)) {
               if ($2==NMTRAN_VERBATIM_CHAR "FIRST") hadpos="\""location"\"" ", first=true"
               if ($2==NMTRAN_VERBATIM_CHAR "LAST") hadpos="\""location"\"" ", last=true"
               sub1=" *\"" "other" " "
   #            sub1=" *\"" OTHER " "  # This does not work for some strange reason
               sub(sub1,"") # inline code such as iseq3
               sub2=sub1 "\\" NMTRAN_VERBATIM_CHAR
               sub(sub2,"") # inline code with NM-TRAN verbatim char
            }
         } else if (isverbatim && $NF && !index($1,";")==1) {
            isverbatim=0
            close_target("",hadpos)
            hadpos=""
         }
         if (isverbatim) {
            target_code[++ntargetcode]= $0
         } else {
            # Look for single line verbatim code
            if ( index($1,"(")==1 || index($1,"CALLFL")==1) { # e.g. (OBSERVATION ONLY)
               sameline()
            } else {
               # Model object output
#                  print "allline: $0="$0
               gsub(/\t/," ")
               # BOV conditional code removal
               if ( hasocc && (match($0,/^ *if *\(OCC\=/) || match($0,/^ *if *\(TRIAL\=/)) ) {
#                  print "getline: $0="$0
                  if (match($0,/ *\{/)) {
#                  print "getline: i="i " j="skipaline " $0="$0
                     do {
                        ++skipaline
                        $0=model_obj[i+skipaline]
                        waselse=match($0,/\} *else *\{/)
#                  print "getline: i="i " j="skipaline " $0="$0
                     } while (match($0,/^ *\}/)==0 || waselse)
#                  print "endline: i="i " j="skipaline " $0="$0
                        ++skipaline
                  }
               }
#
               if (skipaline) {
                  --skipaline
#                  print "skipline: i="i" j="skipaline " $0="$0
               } else {
                  if (useifelse) {
                     print $0
                  } else {
                     splitcomment()
                     hadifblock=findifthenelse()
                     if (!nifblock) {
                        if (hadifblock)
                           if2when() # Print re-formatted when otherwise statements
                        else
                           print $0 comment
                     }
                  }
               } # skipaline
            } # ! sameline
         } # isverbatim
      } # not NMTRAN
   } # for i

} # end mdl_object 

function makeparlist( i) {
   predargs=""
   for (i=1; i<=npredline; i++) {
      $0=pred_code[i]
      predargs=predargs predarg($0)
   }

# Make list of potential arguments for shared parameters
   for (i=1; i<=nerrline; i++) {
      $0=err_code[i]
      if (j=index($0,";"))
         code=substr($0,1,j-1)
      else
         code=$0
      if (match(code,/= *F *[\*|\+|$]/)) hasFpred=1
      predargs=predargs predarg($0)
   }

   if (ndeqline) {
      for (i=1; i<=ndeqline; i++) {
         $0=des_code[i]
         predargs=predargs predarg($0)
      }
   }
   
   gsub(/ /,"",predargs)
   gsub(/^ *\,/,"",predargs)

   # Remove duplicates
   npa=split(predargs,PREDARGS,",")

   for (i=1; i<=npa; i++) {
      arg=PREDARGS[i]
      for (j=1; j<=npa; j++) {
         if (i!=j) {
            if (arg==PREDARGS[j]) PREDARGS[j]=""
         }
      }
   }
   var_list=""
   for (i=1; i<=npa; i++) {
      arg=PREDARGS[i]
      if (arg!="") var_list=var_list" "arg
   }
   predargs=var_list
   
   gsub(/ /,",",predargs)
   sub(/^ *,/,"",predargs)
   nk=split("S D R F ALAG",KEYS)
   for (i=1; i<=nk; i++) {
      CMTNUM[i]=getsuboptnum(var_list,KEYS[i])
   }

   # Get PREDPP parameter names
#print "makeparlist: predargs="predargs
   par_list=getparlist(advan_num,tran_num,ncmt,nk,KEYS,CMTNUM)
#print "makeparlist: par_list="par_list

   nparam=split(predargs,PREDARGS,",")
   for (i=1; i<=nparam; i++) {
      tryarg=PREDARGS[i]
      for (j=1; j<=ncmt; j++) {
         if (CMT[j]==tryarg) CMT[j]=CMT[j]"_m"
      }
   }

} # end makeparlist

function findifthenelse(  i,j,tmp,ii) {
      
   tmp=$0; sub(/[ \t]*/,"",tmp) # comment line may have preceding whitespace
   if (tmp=="") return hadifblock
   #Convert if else to when otherwise
#print "ifelse: everyline $0="$0
   if (match($0,/^ *if */)) {
      hadifblock=1
      if (isif) { # 2 successive if records e.g. if if then else endif
         then=""
         THENBLOCK[nifblock,++nthen]=then
         NTHEN[nifblock]=nthen
         isif=2
      }
      ++nifblock; maxifblock=nifblock; nthen=0; nelse=0; gotelse=0; isif=1
      
      IFBLOCK[nifblock]=$0
#print "ifelse: nifblock="nifblock" $0="$0
      if (!match($0,/\{/)) { # if without then e.g. if (AMT>0) AMT=DOSE
         condition=substr($0,1,index($0,")"))
         sub(/ *if */,"",condition)
         condition=fixcondition(condition)

         then=substr($0,index($0,")")+1)
         sub(/^ */,"",then)
         if (index(then,"Y"listconnect)) {
            ysep=listconnect
            gsub(/ /,"",ysep)
         } else {
            ysep="="
         }
         iys=index(then,ysep)
         if (iys==0) {
            if (ysep==":")
               ysep="="
            else if (ysep=="=")
               ysep=":"
         }

#         rhs=substr(then,1,index(then,ysep)-1)
#print "ifelse: condition="condition" then="then" rhs="rhs
#print then " when " condition " otherwise "rhs
         ii=index(then,"=")
         if (ii) {
            lhsval=substr(then,1,ii-1)
            then=substr(then,ii+1)
         } else {
            lhsval=""
         }
         print lastoffset lhsval " = if " condition " then " then ";"

         nifblock=0; isif=0
      }
      
#print "ifelse: nifblock="nifblock" IFBLOCK["nifblock"]="IFBLOCK[nifblock]
   } else if (nifblock ) {
#print "ifelse: nifblock="nifblock" gotelse="gotelse" $0="$0
      if (match($0,/^ *} *else */)) {
         gotelse=1
#print "ifelse: nifblock="nifblock" gotelse="gotelse" else="$0
      } else if (match($0,/^ *\} */)) { # endif
         gotelse=0
         nifblock--
#print "ifelse: nifblock="nifblock" gotelse="gotelse" endif="$0
      } else if (gotelse==0) { #then
         if (index($0,"Y"listconnect)) {
            ysep=listconnect
            gsub(/ /,"",ysep)
         } else {
            ysep="="
         }
#print "ifelse: ysep="ysep " $0="$0
         gotthen=1
         then=$0
         if (nifblock>1) { # && isif==1) {
            then=substr(then,index(then,ysep)+1)
#print "findifthenelse: then="then
         }
#print "findifthenelse: then="then" nifblock="nifblock" isif="isif
         THENBLOCK[nifblock,++nthen]=then
         NTHEN[nifblock]=nthen
#print "ifelse: nifblock="nifblock" THENBLOCK["nifblock",",nthen"]="THENBLOCK[nifblock,nthen]" NTHEN["nifblock"]="NTHEN[nifblock]
#print "then: hadifblock="hadifblock
      } else if (gotelse) {
#print "ifelse: gotelse="gotelse" $0="$0
         ELSEBLOCK[nifblock,++nelse]=$0
         NELSE[nifblock]=nelse
#print "ifelse: nifblock="nifblock" ELSEBLOCK["nifblock",",nelse"]="ELSEBLOCK[nifblock,nelse]" NELSE["nifblock"]="NELSE[nifblock]
      }
#print "ifelse: endnifblock="nifblock" gotelse="gotelse" $0="$0
      isif=0
   }
   if (!hasifblock) {
      lastoffset=""
      for (i=1; i<=length($0); i++) {
         if (substr($0,i,1)==" ")
            lastoffset=lastoffset " "
         else
            i=length($0)+1 # exit loop
      }
   }
   return hadifblock
} # end of findifthenelse

function if2when( j,k,kk,then,rhs) {
#print "if2when: maxifblock="maxifblock
   nifblock=1
   for (j=1; j<=maxifblock; j++) {
#print "j="j" NTHEN["j"]="NTHEN[j]" NELSE["maxifblock"]="NELSE[maxifblock]
      if (NTHEN[j] != NELSE[maxifblock]) {
         if (NELSE[j]+0==0) { # if then without else
            for (k=1; k<=NTHEN[j]; k++) {
               then=THENBLOCK[j,k]
               gsub(/^ */,"",then)
               rhs=substr(then,1,index(then,ysep)-1)
               gsub(/^ /,"",rhs)
               ELSEBLOCK[j,k]="" #rhs ysep rhs
#print "ifelse: ysep="ysep" rhs="rhs"| thenblock="THENBLOCK[j,k] "| elseblock="ELSEBLOCK[j,k]
               whenotherwise(j,k)
            }
         } else {
            print "nt2mdl error with NMTRAN: THEN statements="NTHEN[j]" not equal to ELSE statements="NELSE[j]
            print "THEN..."
            for (k=1; k<=NTHEN[j]; k++) print "|" THENBLOCK[j,k] "|" 
            print "ELSE..."
            for (k=1; k<=NELSE[j]; k++) print "|" ELSEBLOCK[j,k]"|" 
         }
      } else {
         if (j==maxifblock) {
#print "start="nblock" NTHEN["j"]="NTHEN[j]
            for (k=1; k<=NTHEN[j]; k++) {
               for (kk=1; kk<=maxifblock; kk++) {
#print "ifelse: ysep="ysep"| IFBLOCK["j"]="IFBLOCK[j] "| THENBLOCK["j",",k"]="THENBLOCK[j,k] "| ELSEBLOCK["j",",k"]="ELSEBLOCK[j,k]
                   whenotherwise(kk,k)
               }
            }
         }
      }
      NTHEN[j]=0; NELSE[j]=0
      nifblock++
#print "ifelse: endof maxifblock nifblock="nifblock
   }
   hadifblock=0
   maxifblock=0
   nthen=0; nelse=0; nifblock=0

} # end of if2when

function fixcondition(condition, i,j,k,catvar,catexp,rhs) {
   for (i=1; i<=ncatdef; i++) {
      catvar=CATDEF[i,1]
      for (k=1; k<=nlogic; k++) {
         catexp=catvar LOGIC[k]
         if (j=index(condition,catexp)) {
#            rhs=substr(condition,j+length(catvar)+2)
#            rhs=substr(rhs,1,length(rhs)-1)
#            if ( rhs+0==0 && rhs!="0") {
#print "fixcondition: rhs=|"rhs"| condition="condition
               condition=substr(condition,1,j-1) catexp catvar "." substr(condition,j+length(catvar)+2) 
#            }
         }
      }
   } # ncatdef loop
   return condition
}

function whenotherwise(j,k,  condition,rhs,then,ii,ysep2,terminator,lhsval,lhseq, iflink) {
#print "whenotherwise: ysep="ysep"| IFBLOCK["j"]="IFBLOCK[j] "| THENBLOCK["j",",k"]="THENBLOCK[j,k] "| ELSEBLOCK["j",",k"]="ELSEBLOCK[j,k]
   condition=IFBLOCK[j]
   sub(/ *if */,"",condition)
   sub(/ *\{ */,"",condition)
   condition=fixcondition(condition)

   rhs=ELSEBLOCK[j,k]
   if (rhs=="") {
      if (j==maxifblock)
         terminator=";"
      else
         terminator=""
   } else {
      terminator=""
      ELSEBLOCK[j,k]=""
   }
   ysep2=ysep
#print "whenotherwise: ysep2="ysep2"| rhs="rhs
   ii=index(rhs,ysep2)
   if (ii==0) {
      if (ysep2==":")
         if (index(rhs,"=")) ysep2="="
      else if (ysep2=="=")
         if (index(rhs,":")) ysep2=":"
   }
   if (ysep2==":") {
      ldelim=liststart
      rdelim=listend
   } else {
      ldelim=""
      rdelim=""
   }
#print "whenotherwise: ysep2="ysep2"| rhs="rhs
   rhs=substr(rhs,index(rhs,ysep2)+1,length(rhs)-index(rhs,ysep2))
   then=THENBLOCK[j,k]; gsub(/^ */,"",then)
   if (then=="") return
   ii=index(then,"=")
   if (ii) {
      lhsval=substr(then,1,ii-1)
      then=substr(then,ii+1)
   } else {
      lhsval=""
   }
#print "whenotherwise: condition="condition " then="then " rhs="rhs" lhsval="lhsval

   if (ysep2==":" && !index(then,ldelim))
      printf("%s\n",lastoffset ldelim lhsval " = if " condition " then " then rdelim )
   else {
      if (lhsval=="")
         lhseq="" 
      else
         lhseq=" = "
#print "maxifblock="maxifblock" j="j " rhs="rhs
      if (j==1) {
         iflink=" if "
      } else if (j<=maxifblock) {
         iflink=" elseif "
      }
      printf("%s\n",lastoffset " " lhsval lhseq iflink condition " then " then terminator)
   }
   if (j==maxifblock && rhs!="") {
      printf("%s\n",lastoffset "  else " rhs)
   }
}

function whenotherwise_4_0(j,k,  condition,rhs,then,ii,ysep2,terminator) {
#print "whenotherwise: ysep="ysep"| IFBLOCK["j"]="IFBLOCK[j] "| THENBLOCK["j",",k"]="THENBLOCK[j,k] "| ELSEBLOCK["j",",k"]="ELSEBLOCK[j,k]
   condition=IFBLOCK[j]
   sub(/ *if */,"",condition)
   sub(/ *\{ */,"",condition)
   rhs=ELSEBLOCK[j,k]
   if (rhs=="") {
      if (j==maxifblock)
         terminator=";"
      else
         terminator=","
   } else {
      terminator=""
      ELSEBLOCK[j,k]=""
   }
#print "whenotherwise: ysep="ysep"| rhs="rhs"| index(rhs,ysep)+1="index(rhs,ysep)+1"| length(rhs)-index(rhs,ysep)="length(rhs)-index(rhs,ysep)
   ysep2=ysep
#print "whenotherwise: ysep2="ysep2"| rhs="rhs
   ii=index(rhs,ysep2)
   if (ii==0) {
      if (ysep2==":")
         if (index(rhs,"=")) ysep2="="
      else if (ysep2=="=")
         if (index(rhs,":")) ysep2=":"
   }
   if (ysep2==":") {
      ldelim=liststart
      rdelim=listend
   } else {
      ldelim=""
      rdelim=""
   }
#print "whenotherwise: ysep2="ysep2"| rhs="rhs
   rhs=substr(rhs,index(rhs,ysep2)+1,length(rhs)-index(rhs,ysep2))
   then=THENBLOCK[j,k]; gsub(/^ */,"",then)
#print "whenotherwise: condition="condition " then="then " rhs="rhs" nthen="nthen
   if (then=="") return
   if (ysep2==":" && !index(then,ldelim))
      printf("%s\n",lastoffset ldelim then rdelim " when " condition)
   else
      printf("%s\n",lastoffset then" when " condition terminator)
   if (j==maxifblock) {
      if (rhs!="") {
         printf("%s\n",lastoffset "   otherwise " rhs)
      }
   }
}


###  LIBRARY sub-block
#function	v_cl	v_k	v_cl	vss_cl	a_b
#advan1  ndist=1	V,CL	V,K	.	.	.
#advan2  ndist=1, depot=true	V,CL,KA	V,K,KA	.	.	.
#advan3  ndist=2	.	V,K,K12,K21	CL,V1,Q,V2	CL,V,Q,VSS	ALPHA,BETA,K21
#advan4  ndist=2, depot=true	.	V,K,K23,K32,KA	V2,CL,V2,Q,KA	CL,V,Q,VSS,KA	ALPHA,BETA,K32,KA
#advan11 ndist=3	.	V,K,K12,K21,K13,K31	CL,V1,Q2,V2,Q3,V3	.	ALPHA,BETA,GAMMA,K21,K31
#advan12 ndist=3, depot=true	.	V,K,K23,K32,K24,K42,KA	CL,V2,Q3,V3,Q4,V4,KA	.	ALPHA,BETA,GAMMA,K32,K42,KA
#advan5  glm	.	V,K …	.	.	.
#advan7  glmr	.	V,K …	.	.	.
#advan10 mom1	V,VM, KM	.	.	.	.

function library_subblock( i) {
   PREDPP_list=LIBRETURN"="LIBNAME"("
   if (advan_num==1 || advan_num==2) 
      PREDPP_list=PREDPP_list "ndist=1"
   else if (advan_num==3 || advan_num==4) 
      PREDPP_list=PREDPP_list "ndist=2"
   else if (advan_num==5)
      PREDPP_list=PREDPP_list "glm=true, ncomp=" ncmt
   else if (advan_num==7)
      PREDPP_list=PREDPP_list "glmr=true, ncomp=" ncmt
   else if (advan_num==10)
      PREDPP_list=PREDPP_list "mom1"
   else if (advan_num==11 || advan_num==12) 
      PREDPP_list=PREDPP_list "ndist=3"

   if (advan_num==2 || advan_num==4 || advan_num==12) PREDPP_list=PREDPP_list ", depot=true"
   if (tran_num>0) {
      if (tran_num==1 || tran_num==3) 
         PREDPP_list=PREDPP_list", par=v_k"
      else if (tran_num==2 || tran_num==4) 
         PREDPP_list=PREDPP_list", par=v_cl"
      else if (tran_num==3) 
         PREDPP_list=PREDPP_list", par=vss_cl"
      else if (tran_num==5) {
         PREDPP_list=PREDPP_list", par=abk"
         model_obj[++nmodelobj]="      ## TRANS5 not supported, Using par=abk (TRANS6)"
      } else if (tran_num==6) 
         PREDPP_list=PREDPP_list", par=abk"
   }
#            parout_list=par_list","out_list
#            sub(/,$/,"",parout_list)            
#            parout_list="A,F"
   if (parout_list!="") PREDPP_list=PREDPP_list ","LIBPARAM listconnect listname liststart parout_list listend
#            PREDPP_list=PREDPP_list ",output=list("out_list")"
   LIBLIST[++nliblist]=PREDPP_list")"
   if (!showlib) {
      comment="# "
      lib_subblock[++nlibsubblock]=""
      lib_subblock[++nlibsubblock]=comment "   "MDL_LIB
   } else {
      comment=""
      LIBPARS[nliblist]=par_list
   }

   for (i=1; i<=nliblist; i++) {
      sub(/^ */,"",LIBLIST[i])
      if (showlib) lib_subblock[++nlibsubblock]= comment "      # " LIBNAME " library parameters = "LIBPARS[i]
      lib_subblock[++nlibsubblock]= comment "      "LIBLIST[i]
   }

   if (!showlib) {
      if (showblockend) 
         lib_subblock[++nlibsubblock]=comment "   "blockend"# end "MDL_LIB
      else
         lib_subblock[++nlibsubblock]=comment "   END"# "MDL_LIB
      lib_subblock[++nlibsubblock]=comment ""
   }

}


function compartment_subblock( ncmtline,dist,elim,i,j,np,par,npredargs,maxcmt,iscentral,idepot,icentral,idistrib1,idistrib2,idistrib3,elim1,fromdiv,dist1,dist2,ioutput,dist13,dist21,dist31,elseif,ntype,comment,koutput,tocmt,typecmt,options) {

   if (advan_num==5 || advan_num==7) {
      ########## ADVAN 5 or ADVAN 7 ##########
#      cmt_subblock[++ncmtsubblock]="# advan_num="advan_num
#      cmt_subblock[++ncmtsubblock]="# par_list="par_list
#      cmt_subblock[++ncmtsubblock]="# predargs="predargs
      np=split(par_list,PARLIST,",")
      par_list=""
      for (i=1; i <= np; i++ ) {
         par=PARLIST[i]
         if (match(par,/S[1-9]/))
            Fscale=par
         else if (!match(par,/K[1-9][0-9]/)) 
            par_list=par_list par ","
      }

      npredargs=split(predargs,PREDARGS,",")
      nglmpar=0
      for (j=1; j <= npredargs; j++ ) {
         par=PREDARGS[j]
         if (match(par,/K[1-9][0-9]/)) GLMPAR[++nglmpar]=par
      }
      glmpars=""
      idepdest=-99
      for (i=1; i <= nglmpar; i++ ) {
        par=GLMPAR[i]
        glmpars=glmpars GLMPAR[i] ","
      }
#cmt_subblock[++ncmtsubblock]="#glmpars"glmpars
      par_list=glmpars par_list
      sub(/,$/,"",par_list)
      np=split(par_list,PARLIST,",")
      glmcmts=""
      prediction_compartment_name="CENTRAL"; icentral=1
      for (i=1; i <= ncmt; i++ ) {
         if (CMT[i]=="CENTRAL") {
            prediction_compartment_name=CMT[i]
            icentral=i
         }
         glmcmts=glmcmts CMT[i] " "
      }
#cmt_subblock[++ncmtsubblock]="#"glmcmts

      idepot=1
      maxcmt=getmaxcmt()
      if (maxcmt==(idepot-1)) maxcmt=idepot

      ncmtline=0; ndirline=0

      for (i=1; i<=ncmt; i++) {
         if (i==idepot || LAG_CMT[i]!="" || F_CMT[i]!="") {
            for (j=1; j <= nglmpar; j++ ) {
              par=GLMPAR[j]
              if (index(par,"K"i)) {
                 idepdest=substr(par,3)
              }
            }
            cmt_line[++ncmtline]="      "CMT[i] listconnect  "  "  liststart "type is depot, modelCmt="i admtype ", to="CMT[idepdest]", ka=K"i idepdest LAG_CMT[i] F_CMT[i] listend 
            icmtdest=-99
            for (j=1; j <= nglmpar; j++ ) {
               par=GLMPAR[j]
               if (index(par,"K"i)) {
                  icmtdest=substr(par,3)
                  if (icmtdest==0) {
                     if (useelimname)
                        ELIMNAME="ELIM"i
                     else
                        ELIMNAME=":"
                     cmt_line[++ncmtline]="        " ELIMNAME listconnect "   "  liststart "type is elimination, modelCmt="i", from="CMT[i]", k=K"i"0" listend
                  }
               }
            }
         } else if (i==icentral) {
            cmt_line[++ncmtline]="      "CMT[icentral] listconnect   "   "  liststart "type is compartment, modelCmt="icentral listend
            if (useelimname)
               ELIMNAME="ELIM"i
            else
               ELIMNAME=":"
            cmt_line[++ncmtline]="        " ELIMNAME listconnect "   "  liststart "type is elimination, modelCmt="icentral", from="CMT[icentral]", k=K"icentral"0"  R_CMT[i] D_CMT[i] listend
         } else {
            icmtdest=-99
            for (j=1; j <= nglmpar; j++ ) {
               par=GLMPAR[j]
               if (index(par,"K"i)) {
                  icmtdest=substr(par,3)
                  if (icmtdest==0) {
                     if (useelimname)
                        ELIMNAME="ELIM"i
                     else
                        ELIMNAME=":"
                     cmt_line[++ncmtline]="        " ELIMNAME listconnect "   "  liststart "type is elimination, modelCmt="i", from="CMT[i]", k=K"i"0" listend
                  } else {
                     if (usetransfername) {
                        cmt_line[++ncmtline]="      "CMT[i] listconnect "   "  liststart "type is transfer, modelCmt="i", to="CMT[icmtdest]", from="CMT[i]", kt=K"i icmtdest listend
                     } else {
                        cmt_line[++ncmtline]="      "CMT[i] listconnect "   "  liststart "type is compartment, modelCmt="i listend
                        cmt_line[++ncmtline]="        :"    listconnect "   "  liststart "type is transfer, modelCmt="i", to="CMT[icmtdest]", from="CMT[i]", kt=K"i icmtdest listend
                     }
                  }
               }
            }
         }
      }

   } else {
      ########## STANDARD ADVANS ##########

      if (!ndeqline) ncmt=0
      if (advan_num==2 || advan_num==4 || advan_num==12) { # depot compartment (ncmt0) is 1 for compatibility with NONMEM default
         ncmt0=1
         isdepot=1
      } else {
         ncmt0=1
         isdepot=0
      }
      idepot=ncmt0
      icentral=idepot+isdepot
      idistrib1=icentral
      idistrib2=icentral+1
      idistrib3=idistrib2+1
      if (isdepot)
         iscentral=", central=true"
      else
         iscentral=""
      if (tran_num==2 || tran_num==3 || tran_num==4) {
         if (advan_num==1 || advan_num==2) {
            if (isdepot) {
               fromdiv="/V"
               dist1="volume=V"
            } else {
               fromdiv="/V"
               dist1="volume=V"
            }
            elim1="v=V, cl=CL"
            koutput="CL/V"
            ioutput=idistrib1+1
         } else if (advan_num==3 || advan_num==4) {
            if (isdepot) {
               fromdiv="/V2"
               dist1="volume=V2"
               elim1="v=V2, cl=CL"
               koutput="CL/V2"
            } else {
               fromdiv="/V1"
               dist1="volume=V1"
               elim1="v=V1, cl=CL"
               koutput="CL/V1"
            }

            if (tran_num==3) {
               if (isdepot) {
                  dist2="volume=VSS-V2, cl=Q"
               } else {
                  dist2="volume=VSS-V1, cl=Q"
               }
            } else {
               if (isdepot) {
                  dist2="kin=Q/V2, kout=Q/V3"
               } else {
                  dist2="kin=Q/V1, kout=Q/V2"
               }
            }
            ioutput=idistrib2+1
         } else if (advan_num==11 || advan_num==12) {
            if (isdepot) {
               fromdiv="/V2"
               dist1="volume=V2"
               dist2="kin=Q3/V2, kout=Q3/V3"
               dist3="kin=Q4/V2, kout=Q4/V4"
               elim1="v=V2, cl=CL"
               koutput="CL/V2"
            } else {
               fromdiv="/V1"
               dist1="volume=V1"
               dist2="kin=Q2/V1, kout=Q2/V2"
               dist3="kin=Q3/V1, kout=Q3/V3"
               elim1="v=V1, cl=CL"
               koutput="CL/V1"
            }
            ioutput=idistrib3+1
         }
      } else if (!deqline) { # tran_num=1
         fromdiv=""
         if (advan_num==1 || advan_num==2 || advan_num==10) {
            if (advan_num==10) {
               elim1="v=V, vm=VM*V, km=KM"
               koutput="VM/(KM+CENTRAL/V)" # VM*V/(KM+CENTRAL/V)/V
            } else {
               elim1="v=V, k=K"
               koutput="K"
            }
            dist1="volume=V"
            ioutput=idistrib1+1
         } else if (advan_num==3 || advan_num==4) {
            if (isdepot) {
               dist1="volume=V" #, kout=K23, to="idistrib2
               dist21="kin=K23, kout=K32"
            } else {
               dist1="volume=V" #, kout=K12, to="idistrib2
               dist21="kin=K12, kout=K21"
            }
            elim1="v=V, k=K"
            koutput="K"
            ioutput=idistrib2+1
         } else if (advan_num==11 || advan_num==12) {
            if (isdepot) {
               dist1="volume=V" #, kout=K23, to="idistrib2
               #dist13="kout=K24, to="idistrib3
               dist21="kin=K23, kout=K32"
               dist31="kin=K24, kout=K42"
            } else {
               dist1="volume=V" #, kout=K12, to="idistrib2
               #dist13="kout=K13, to="idistrib3
               dist21="kin=K12, kout=K21"
               dist31="kin=K13, kout=K31"
            }
            elim1="v=V, k=K"
            koutput="K"
            ioutput=idistrib3+1
         }
      } # tran_num=1
  

      maxcmt=getmaxcmt()
      if (maxcmt==(idepot-1)) maxcmt=idepot
      if (ndeqline) maxcmt=ncmt

      ncmtline=0; ndirline=0
      icmt=0; icentral=0; ndepcmt=0

      for (idepcmt=idepot; idepcmt<=maxcmt; idepcmt++) {

         options=R_CMT[idepcmt] D_CMT[idepcmt] LAG_CMT[idepcmt] F_CMT[idepcmt]
#print "compartment_subblock: isdepot="isdepot" idepcmt="idepcmt" options="options

      # Input compartments
         prediction_compartment_name="CENTRAL"

        if (hasrate && !ndeqline) {
           if (R_CMT[idepcmt]!= "") {
              DEPCMT[++ndepcmt]="INPUT_RATE"
              cmt_line[++ncmtline]="      "DEPCMT[ndepcmt] listconnect liststart "type is direct, modelCmt="idepcmt admtype ", to=CENTRAL" R_CMT[idepcmt] LAG_CMT[idepcmt] F_CMT[idepcmt] listend 
           }
           if (D_CMT[idepcmt]!= "") {
              DEPCMT[++ndepcmt]="INPUT_DUR"
              cmt_line[++ncmtline]="      "DEPCMT[ndepcmt] listconnect " " liststart "type is direct, modelCmt="idepcmt admtype ", to=CENTRAL" D_CMT[idepcmt] LAG_CMT[idepcmt] F_CMT[idepcmt] listend 
           }
           if (1==0) { # Not currently supported
              DEPCMT[++ndepcmt]="INPUT_KA"
              cmt_line[++ncmtline]="      "DEPCMT[ndepcmt] listconnect "  "liststart "type is depot, modelCmt="idepcmt admtype ", to=CENTRAL, ka=KA" LAG_CMT[idepcmt] F_CMT[idepcmt] listend 
           }
           #DEPCMT[++ndepcmt]="INPUT_BOK0"
           #cmt_line[++ncmtline]="      "DEPCMT[ndepcmt] listconnect liststart "type=direct, modelCmt="idepcmt admtype ", to=CENTRAL" LAG_CMT[idepcmt] F_CMT[idepcmt] listend
           if (!isdepot) icentral=idepcmt
        }
        if (!isdepot) {
           if (ndeqline) {
              if (options=="")
                  tocmt=""
              else {
                 DEPCMT[ndepcmt]="INPUT_" CMT[idepcmt]
                 tocmt=CMT[idepcmt]
                 if (usedeqdirect)
                    typecmt="direct"
                 else
                    typecmt="depot"
                 if (usedeqto)
                    toname="to"
                 else
                    toname="target"
              }
           } else {
              if (options=="" || hasrate)
                  tocmt=""
              else {
                 DEPCMT[ndepcmt]="INPUT_BOK0"
                 tocmt="CENTRAL"
                 typecmt="direct"
                 toname="to"
              }
           }
           if (tocmt!="") cmt_line[++ncmtline]="      "DEPCMT[ndepcmt] listconnect liststart "type is "typecmt", modelCmt="idepcmt admtype ", "toname"="tocmt options listend
           icentral=idepcmt-1
#print "maxcmt: icentral="icentral
        }

        if (isdepot && (i==idepot || LAG_CMT[idepcmt]!="" || F_CMT[idepcmt]!="")) {
           DEPCMT[++ndepcmt]="INPUT_KA"
           cmt_line[++ncmtline]="      "DEPCMT[ndepcmt] listconnect "  "  liststart "type is depot, modelCmt="idepcmt admtype ", to=CENTRAL, ka=KA" LAG_CMT[idepcmt] F_CMT[idepcmt] listend 
           if (hascmt) {
              icentral=++idepcmt
              if (R_CMT[idepcmt]!= "") {
                 DEPCMT[ndepcmt]="INPUT_RATE"
                 cmt_line[++ncmtline]="      "DEPCMT[ndepcmt] listconnect liststart "type is direct, modelCmt="idepcmt admtype ", to=CENTRAL" R_CMT[idepcmt] LAG_CMT[idepcmt] F_CMT[idepcmt] listend 
              }
              if (D_CMT[idepcmt]!= "") {
                 DEPCMT[ndepcmt]="INPUT_DUR"
                 cmt_line[++ncmtline]="      "DEPCMT[ndepcmt] listconnect liststart "type is direct, modelCmt="idepcmt admtype ", to=CENTRAL" D_CMT[idepcmt] LAG_CMT[idepcmt] F_CMT[idepcmt] listend 
              }
              DEPCMT[ndepcmt]="INPUT_BOK0"
              cmt_line[++ncmtline]="      "DEPCMT[ndepcmt] listconnect liststart "type is direct, modelCmt="idepcmt admtype ", to=CENTRAL" LAG_CMT[idepcmt] F_CMT[idepcmt] listend 
           } else
              icentral=idepcmt+1
        }
      } # end maxcmt loop

#print "compartment_subblock: standard advan ndeqline="ndeqline" ncmt="ncmt
         if (ndeqline) {
            ndeqcmtline=ncmtline
            return
         }

         # used to create link in linkcmtmdl()
         for (i=1; i<=ndepcmt; i++) CMT[i]=DEPCMT[i]

         # Elimination compartment

         if (icentral==0) icentral=1
         cmt_line[++ncmtline]="      CENTRAL" listconnect "   "  liststart "type is compartment, modelCmt="icentral listend
         if (useelimname)
            ELIMNAME="ELIM"i
         else
            ELIMNAME=":"
         cmt_line[++ncmtline]="        " ELIMNAME listconnect "   "  liststart "type is elimination, modelCmt="icentral", from=CENTRAL, " elim1 listend
         CMT[icentral]="CENTRAL"

         # Distribution compartments
         idistcmt=icentral
         if (advan_num==3 || advan_num==4) {
           if (tran_num==3 || tran_num==4) {
              cmt_line[++ncmtline]="      PERIPHERAL" listconnect liststart "type is distribution, modelCmt="++idistcmt", from=CENTRAL, " dist2 listend
           } else if (tran_num==1) {
              cmt_line[++ncmtline]="      PERIPHERAL" listconnect liststart "type is distribution, modelCmt="++idistcmt", from=CENTRAL, " dist21 listend
           }
           CMT[idistcmt]="PERIPHERAL"
         }

         if (advan_num==11 || advan_num==12) {
           if (tran_num==4) {
              cmt_line[++ncmtline]="      SHALLOW" listconnect  "   " liststart "type is distribution, modelCmt="++idistcmt", from=CENTRAL, " dist2 listend
              cmt_line[++ncmtline]="      DEEP" listconnect  "      " liststart "type is distribution, modelCmt="++idistcmt", from=CENTRAL, " dist3 listend
           } else if (tran_num==1) {
              cmt_line[++ncmtline]="      SHALLOW" listconnect "   " liststart "type is distribution, modelCmt="++idistcmt", from=CENTRAL, " dist21 listend
              cmt_line[++ncmtline]="      DEEP" listconnect "      " liststart "type is distribution, modelCmt="++idistcmt", from=CENTRAL, " dist31 listend
           }
           CMT[idistcmt-1]="SHALLOW"
           CMT[idistcmt]="DEEP"
         }
         if (F_CMT[0]!="") {
            cmt_line[++ncmtline]="      OUTPUT" listconnect "    " liststart "type is compartment,  modelCmt="++idistcmt listend
            cmt_line[++ncmtline]="            "           "      " liststart "type is transfer,  modelCmt="idistcmt", from=CENTRAL, to=OUTPUT, kt="koutput "*" F_CMT[0] listend
            CMT[idistcmt]="OUTPUT"
         }
         # ncmt defines list of names in CMT[] which are used for replacement of A(*) and for adding link to AMT in data object
         ncmt=idistcmt
   } # end standard ADVAN

   if (showlib) {
         comment="# "
         cmt_subblock[++ncmtsubblock]=""
         cmt_subblock[++ncmtsubblock]=comment "   "MDL_CMT blockstart
   } else {
      cmt_subblock[++ncmtsubblock]="      ##  Parameters = "par_list
      comment=""
   }

   for (i=1; i<=ncmtline; i++) {
      cmt_subblock[++ncmtsubblock]=comment cmt_line[i]
   }

   if (showblockend) 
      cmt_subblock[++ncmtsubblock]=comment"   "blockend"# end "MDL_CMT
   else
      cmt_subblock[++ncmtsubblock]=comment"   END"# "MDL_CMT

   cmt_subblock[++ncmtsubblock]=comment
   cmt_subblock[++ncmtsubblock]=comment"      F="prediction_compartment_name"/"Fscale

} # end compartment_subblock


function getmaxcmt() {
   np=split(par_list,PARLIST,",")
   maxcmt=idepot-1
   zerocmt()
   for (i=1;i<=np;i++) {
      par=PARLIST[i]
      if (index(par,"ALAG")==1) {
        num=substr(par,5)
        LAG_CMT[num]=", tlag="par
      }
      maxcmt=addncmt(num,par)
      if (index(par,"F")==1) {
        num=substr(par,2)
        if (num+0>0)
           F_CMT[num]=", finput="par # equivalent to Monolix macro attribute p
        else if (num=="O" || num=="0")
           F_CMT[0]=par
      }
      maxcmt=addncmt(num,par)
      if (index(par,"R")==1) {
        num=substr(par,2)
        R_CMT[num]=", modelRate="par
      }
      maxcmt=addncmt(num,par)
      if (index(par,"D")==1) {
        num=substr(par,2)
        D_CMT[num]=", modelDur="par
      }
      maxcmt=addncmt(num,par)
      if (index(par,"S")==1) {
        num=substr(par,2)
        S_CMT[num]=", volume="par
        Fscale=par
      }
      maxcmt=addncmt(num,par)
   }
   return maxcmt
}

function addncmt(num,par, j,gotcmt) {
   if (num=="O" || par=="") return maxcmt
   gotcmt=0
   for (j=1; j<=maxcmt; j++) {
      if (N_CMT[j]==num) { 
      gotcmt=1;
      j=maxcmt+1
      } else {
      }
   }
   if (!gotcmt) {
      N_CMT[++maxcmt]=num
   }
   return maxcmt
}


function zerocmt( i) {
   for (i=0; i<=100; i++) { LAG_CMT[i]=""; F_CMT[i]=""; R_CMT[i]=""; D_CMT[i]="" ; S_CMT[i]=""; N_CMT[i]=""}
}

function hascols() {
   hasamt=0; hasrate=0; hascmt=0; hasadm=0; hasmdv=0; hasevid=0; hasdvid=0; hasocc=0; hasid=0; hasl2=0

   idepcmt=idepot
   for (i=1; i<=ninput; i++) {
      if (!DROP[i]) { 
         item=INPUT[i]
         if (j=index(item,";")) {
            item=substr(item,j-1)
         }
         if (item=="AMT")
            hasamt=1
         else if (item=="RATE")
            hasrate=1
         else if (item=="CMT") {
            hascmt=1
            idepcmt="CMT"
            CMTNAME="CMT"
         } else if (item=="ADM") {
            hasadm=1
            CMTNAME="ADM"
         } else if (item=="MDV")
            hasmdv=1
         else if (item=="EVID")
            hasevid=1
         else if (item=="DVID") {
            hasdvid=1
            DVIDNAME="DVID"
         } else if (item=="OCC")
            hasocc=1
         else if (item=="ID")
            hasid=1
         else if (item=="L2")
            hasl2=1
      }
   }
}

function deq_subblock(  i) {

   compartment_subblock()

   if (ndeqcmtline) {
      model_obj[++nmodelobj]=""
      model_obj[++nmodelobj]="   "MDL_CMT blockstart
      model_obj[++nmodelobj]="      ##  Parameters = "par_list

      for (i=1; i<=ndeqcmtline; i++) {
         model_obj[++nmodelobj]= cmt_line[i]
      }
      model_obj[++nmodelobj]="   "blockend "# end " MDL_CMT
   }
   model_obj[++nmodelobj]=""
   model_obj[++nmodelobj]="   "MDL_DEQ blockstart
   gotlocation=0
   for (i=1; i<=ndeqline; i++) {
      $0=des_code[i]
      if (!(i==1 && $0!="")) {
         if (translate()) {
            # check for unnecessary assignment e.g. C=A(1) -> C=C
            odevar=$0
            gsub(/ /,"",odevar)
            split(odevar,ODEVAR,"=")
            if (ODEVAR[1]!=ODEVAR[2]) model_obj[++nmodelobj]="   "$0
         }
      }
      if (!gotlocation) { model_location[nmodelobj]="$DES"; gotlocation=1 }
   }


   for (i=1; i<=ncmt; i++) {
      $0=DADT[i]
      j=index($0,";")
      if (j) {
         comment=" #"substr($0,j+1)
         $0=substr($0,1,j-1)
      } else {
         comment=""
      }
      if ($0!="") {
         gsub(/  /,"")
         if (translate()) {
            deriv=$0
            if (AZERO[i]=="") {
               AZERO[i]=0
#               if (useinitzero)
                  ode_init=""
#               else
#                 ode_init=", init=0"
            } else {
               ode_init=", init="AZERO[i]
            }
            if (idvname=="TIME") {
               ode_wrt=""
            } else {
               ode_wrt=", wrt="idvname
            }

            model_obj[++nmodelobj]="      "CMT[i] listconnect listname liststart "deriv="deriv ode_init ode_wrt listend comment
         }
      }
   }


   if (showblockend)
      model_obj[++nmodelobj]="   "blockend"# end "MDL_DEQ
   else
      model_obj[++nmodelobj]="   END"# "MDL_DEQ
}

function open_target(target,location,relpos) {
   if (location=="") location="INLINE"
   ntargetcode=0
   if (usefulltask==1) {
      target_code[++ntargetcode]=MDL_TARGET"(target="target ",location=\"" location "\""relpos")" verbatimblockstart
   } else {
      target_code[++ntargetcode]=MDL_TARGET"(target=\"NONMEM\")" verbatimblockstart
  }
}

function close_target(destination, hadpos, comment) {
# This is where !usetargetcode has to be tested to stop printing of target code
   if (!usetargetcode) 
      comment="#"
   else
      comment=""

   if (showblockend) { 
      target_code[++ntargetcode]=verbatimblockend" # end "MDL_TARGET
   }
   if (hadpos!="") {
       sub(/\"INLINE\"/,"",target_code[1])
       sub(/\)\{/, hadpos "){",target_code[1])
   }
   if (destination=="")
      for (i=1; i<=ntargetcode; i++) print comment target_code[i]
   else if (destination=="parm")
      for (i=1; i<=ntargetcode; i++) parm_obj[++nparmobj]=comment target_code[i]
   else if (destination=="task")
      for (i=1; i<=ntargetcode; i++) task_obj[++ntaskobj]=comment target_code[i]
}

function sameline() {
   open_target(MDL_NMTRAN,location,",sameline=true")
   target_code[++ntargetcode]= $0
   close_target("")
}

function priorblock( i,j,saveline,npri,priopt) {
   parm_obj[++nparmobj]="   "MDL_PRIOR blockstart""
   gotlocation=0; saveline=$0
   for (i=1; i<=npriline; i++) {
      $0=pri_code[i]
      if (NF) {
         gsub(/ *\= */,"=")
         if (translate()) {
            npri=split($0,NPRI)
            for (j=1; j<=npri; j++) {
               priopt=NPRI[j]
               if (index(priopt,"=")==0) priopt=priopt"=true"
               parm_obj[++nparmobj]="      "priopt
            }
         }
      }
   }
   if (showblockend) parm_obj[++nparmobj]="   "blockend"# end "MDL_PRIOR
   parm_obj[++nparmobj]=""
   $0=saveline
}


function mixblock( i,j,k,mixline,mixcom,nmix,pnum) {
#   if (!outputmix) {
#      model_obj[++nmodelobj]="# "MDL_MIX" not yet implemented in MDL Editor"
#      return
#   }

   model_obj[++nmodelobj]="   "MDL_MIX blockstart

#   MIXTURE{
#      mixtures=list("MIX1", "MIX2")
#      MIX1=POP_PROB_CYP_SLOW
#      MIX2=1-POP_PROB_CYP_SLOW
#   }

   gotlocation=0; saveline=$0
   # find NSPOP
   for (i=1; i<=nmixline; i++) {
      $0=mix_code[i]
      if (NF) {
         j=(index(toupper($0),";"))
         if (j) {
            mixline=substr($0,1,j-1)
         } else {
            mixline=$0
         }
         j=match(toupper(mixline),/^ *NSPOP *\=/)

         if (j) {
             k=index(mixline,"=")
             nmix=substr(mixline,k+1)+0
         }
      }
    } # find NSPOP
    # Build mixtures list
    mixline="mixtures" listconnect listname liststart
    for (j=1; j<=nmix; j++) {
       mixline=mixline "MIX"j
       if (j==nmix)
           mixline=mixline listend
       else
           mixline=mixline ","
    }

   model_obj[++nmodelobj]="   "mixline
   if (!gotlocation) { model_location[nmodelobj]="$MIXTURE"; gotlocation=1 }

   for (i=1; i<=nmixline; i++) {
      $0=mix_code[i]
      if (NF) {
         j=(index(toupper($0),";"))
         if (j) {
            mixcom=substr($0,j)
            $0=substr($0,1,j-1)
         } else {
            mixcom=""
         }

         j=match(toupper($0),/^ *NSPOP *\=/)
         if (!j) {
            for (j=1; j<=nmix; j++) {
               pnum="P\\("j"\\)"
               sub(pnum,"MIX"j,$0)
            }
            if (translate()) {
               $0=$0 mixcom
               model_obj[++nmodelobj]="   "$0
            }
         }
      }
   }

   if (showblockend) model_obj[++nmodelobj]="   "blockend"# end "MDL_MIX
   model_obj[++nmodelobj]=""
   $0=saveline
}
function observation_block(  hasy,i,iruv,iruv2,ico,ieq,mathop,predmult,predop,j,blockstart,flagline,ndvid) {
      condition=""; condexpr=""; likelihood=""; reserror=""; 
      savezero=$0; nliketype=0; ndvid=0
      hasy=0; hasflag=0; hadflag=0

      for (i=1; i<=20; i++) NLCB[i]=0 # initalize counter for likelihood blocks
#model_obj[++nmodelobj]= "observation_block nlikline="nlikline

      if (!addobj) {
         for (i=1; i<=nlikline; i++) {
            $0=toupper(lik_code[i])
            if ((match($0,/^ *IF *\(/) && match($0,/THEN/)) || match($0,/^ *ELSE/) ) {
               blockstart=i
#   model_obj[++nmodelobj]= "observation_block blockstart="blockstart" $0="$0
            }
            if (match($0,/F_FLAG/)) {
               flagline=lik_code[i]
               lik_code[i]="#DROP"
               nlikline++
#   model_obj[++nmodelobj]= "observation_block nlikeline="nlikeline" blockstart="blockstart" $0="$0
               for (j=nlikline; j>=blockstart; j--) {
#   model_obj[++nmodelobj]= "observation_block lik_code["j-1"]="lik_code[j-1]

                  lik_code[j]=lik_code[j-1]
               }
               lik_code[blockstart]=flagline
            }
         }
      }

      for (i=1; i<=nlikline; i++) {
         $0=lik_code[i]
         if (translate()) {
#   model_obj[++nmodelobj]= "observation_block i="i" $0="$0
            if (match($0,/^ *#/)) {
   #model_obj[++nmodelobj]= "observation_block i="i" comment:"$0 


   #### NOTE this F_FLAG code will not work properly if F_FLAG is assigned a value by a variable
   #### NOTE F_FLAG appears before if {} else {} block to which it belongs
            } else if (match(toupper($0),/F_FLAG *= *0/)) {
               nliketype++;  hasflag=1 ; hadflag=1
               LIKETYPE[nliketype]=TYPECONTINUOUS
               estrectype=""
   #model_obj[++nmodelobj]=  "observation_block i="i" LIKETYPE["nliketype"]="LIKETYPE[nliketype] " $0="$0
            } else if (match(toupper($0),/F_FLAG *= *1/)) {
               nliketype++;  hasflag=1 ; hadflag=1
               LIKETYPE[nliketype]=TYPELIKELIHOOD
               estrectype=""
   #model_obj[++nmodelobj]=  "observation_block i="i" LIKETYPE["nliketype"]="LIKETYPE[nliketype] " $0="$0
            } else if (match(toupper($0),/F_FLAG *= *2/)) {
               nliketype++;  hasflag=1 ; hadflag=1
               LIKETYPE[nliketype]=TYPEM2LL
               estrectype=""
   #model_obj[++nmodelobj]=  "observation_block i="i" LIKETYPE["nliketype"]="LIKETYPE[nliketype] " $0="$0

            } else if ($1=="if" || $3=="if") {
               if (!hasflag) {
                  nliketype++
               } else {
                 hasflag=0
               }
               ilb=index($0,"(")
               irb=index($0,")")
               condition=substr($0,ilb+1, irb-ilb-1)
               if ($3=="if")
                  condexpr="else if"
               else
                  condexpr="if"
   #model_obj[++nmodelobj]=  "observation_block i="i" LIKETYPE["nliketype"]="LIKETYPE[nliketype] " $0="$0
               PREDEXPR[nliketype,5]=condexpr
               PREDEXPR[nliketype,6]=condition

            } else if ($2=="else") {
               if (!hasflag) {
                  nliketype++
               } else {
                 hasflag=0
               }
               condition=""
               condexpr="else"
               PREDEXPR[nliketype,5]=condexpr
               PREDEXPR[nliketype,6]=condition
   #model_obj[++nmodelobj]=  "observation_block i="i" LIKETYPE["nliketype"]="LIKETYPE[nliketype] " $0="$0

            } else if (match(toupper($0),/^ *Y *=/)) {
   #model_obj[++nmodelobj]=  "observation_block i="i" hadflag="hadflag" nliketype="nliketype " $0="$0
               if (!hadflag && nliketype==0) { nliketype++ } # unconditional prediction
               if (LIKETYPE[nliketype]=="" && estrectype!="") {
                   # use type from estimation record
                   LIKETYPE[nliketype]=estrectype
               } 
               liketype=LIKETYPE[nliketype]  #LIKETYPE[npredexpr]
   #model_obj[++nmodelobj]=  "observation_block i="i" LIKETYPE["nliketype"]="LIKETYPE[nliketype] " $0="$0
               ieq=index($0,"=")
               ico=index($0,"#")
               if (liketype==TYPECONTINUOUS ) { 
                  iruv=match($0,/[+|*]/)
                  if (iruv) {
                     ruvexpr=substr($0,iruv)
                     gsub(/ /,"",ruvexpr)
                     iruv2=match(ruvexpr,/[\*(1+eps|+eps]/)
                     iruv=iruv+iruv2-1
                     mathop=substr(ruvexpr,iruv2-1,1)
                     ruvexpr=substr(ruvexpr,iruv2+1)
                     prediction=substr($0,ieq+1,iruv-ieq-1)
   #model_obj[++nmodelobj]="observation_block length prediction="length(prediction)" prediction="prediction
                  } else {
                     mathop=""; ruvexpr=""
                     prediction=substr($0,ieq+1)               
                  }
   #model_obj[++nmodelobj]="observation_block i="i" $0="$0 "prediction="prediction" mathop="mathop" ruvexpr="ruvexpr
                  sub(/ *$/,"",prediction)
                  if (estrectype!=TYPECONTINUOUS && match(prediction,/^ *ln\(/)) {
                     transform=",transform=ln"
                     sub(/ln\(/,"",prediction)
                     sub(/\)/,"",prediction)
                  } else {
                     transform=""
                  }
                  predmult=prediction
   # Prediction could be C1 + C2 if  Y=C1 + C2*RUV_CV + RUV_SD
   # So distinguish additive part from multiplied part
   #model_obj[++nmodelobj]="observation_block j="j" $0="$0 " prediction="prediction" predmult="predmult
   ### Doesn't work
   #               for (j=length(prediction); j<=0; j--) {
   #                 predop=substr(prediction,j,1)
   #model_obj[++nmodelobj]="observation_block j="j" prediction="prediction" predop="predop" predmult="predmult
   #                 if (index(predop,"+") || index(predop,"-")) {
   #                    predmult=substr(prediction1,j+1)
   #model_obj[++nmodelobj]="observation_block j="j" prediction="prediction" predop="predop" predmult="predmult
   #                    j=0
   #                 }
   #               }
                  ico=index(ruvexpr,"#")
                  if (ico) ruvexpr=substr(ruvexpr,1,ico-1)
   #print  "observation_block ruvexpr="ruvexpr
                  if (match(tolower(ruvexpr),/[eta|eps]_/)) { 
                     if (mathop=="*") {
   # predmult is subtracted from ruvexpr so that MCL type=continuous with prediction and ruv attributes
   # can be translated to prediction + ruv for NM-TRAN
                        reserror="ruv="predmult "*" ruvexpr "-" predmult  transform
                     } else {
                        reserror="ruv="ruvexpr transform
                     }
                  } else
                     reserror="error~(type="NORMDIST",mean=0,sd="ruvexpr transform
   #print "CONT CONT: liketype="liketype" "prediction" " reserror
               } else {
                  ruvexpr=""
                  reserror=""
                  if (ico)
                     prediction=substr($0,ieq+1,ico-ieq-1)
                  else
                     prediction=substr($0,ieq+1)

   #model_obj[++nmodelobj]=  "NOT CC i="i" nliketype="nliketype" ico="ico" liketype="liketype" prediction="prediction" $0="$0
               }
   #print "prediction="prediction" ruvexpr="ruvexpr" mathop="mathop" transform="transform

               PREDEXPR[nliketype,1]=liketype
               PREDEXPR[nliketype,2]=prediction
               PREDEXPR[nliketype,3]=reserror
               PREDEXPR[nliketype,4]=ruvexpr
               PREDEXPR[nliketype,7]=mathop
               lik_code_block[nliketype,++NLCB[nliketype]]="#PREDICTION"
   #model_obj[++nmodelobj]= "observation_block i="i" liketype="liketype " prediction="prediction
   #print ""
               hasy=1
            } else if ($1!="}") {
               lik_code_block[nliketype,++NLCB[nliketype]]=$0
   #            model_obj[++nmodelobj]= "observation_block lik_code_block["nliketype","NLCB[nliketype]"]="$0 " condexpr="condexpr
            }
         }
      }
      

#      if (1 || hasy) {
#model_obj[++nmodelobj]="observation_block estrectype=|"estrectype"|"
         for (i=1; i<=nliketype; i++) {
            liketype=PREDEXPR[i,1]
            prediction=PREDEXPR[i,2]
            reserror=PREDEXPR[i,3]
            ruvexpr=PREDEXPR[i,4]
            condexpr=PREDEXPR[i,5]
            condition=PREDEXPR[i,6]
            mathop=PREDEXPR[i,7]
#model_obj[++nmodelobj]="observation_block nliketype="nliketype" i="i " condexpr="condexpr
            if (estrectype == TYPECONTINUOUS) {
#               predrec="Y = "prediction mathop ruvexpr
               predrec=YDVID[++ndvid]" = "prediction mathop ruvexpr
               #preddist=YDVID[ndvid]" ~ " liststart prediction "," reserror listend
               preddist="" # suppress printing of example of residual error as a distribution
#model_obj[++nmodelobj]="observation_block prediction="prediction" mathop="mathop" ruvexpr="ruvexpr " predrec="predrec
            } else {
               if (liketype==TYPECONTINUOUS) {
                  predrec=YDVID[++ndvid] listconnect listname liststart "type="liketype",prediction="prediction","reserror listend
               } else {
                  predrec=YDVID[++ndvid] listconnect listname liststart "type="liketype",prediction="prediction listend
               }
               preddist=""
            }

            if (addobj) {
#model_obj[++nmodelobj]="observation_block addobj $0="$0 " ipredexpr="i "nliketype="nliketype" condexpr="condexpr
               if (condexpr=="") {
                     $0=predrec
#model_obj[++nmodelobj]="observation_block addobj $0="$0 " ipredexpr="i "nliketype="nliketype
                     amt_reference()
                     model_obj[++nmodelobj]="      "$0
                     if (preddist!="") model_obj[++nmodelobj]="#      "preddist
               } else {
#model_obj[++nmodelobj]="observation_block addobj $0="$0 " ipredexpr="i "nliketype="nliketype
                  if (useifelse) {
                     if (condexpr=="else")
                        model_obj[nmodelobj]="      } "condexpr" {"
                     else if (condexpr=="else if")
                        model_obj[nmodelobj]="      } "condexpr" ("condition") {"
                     else if (condexpr=="if")
                        model_obj[++nmodelobj]="      "condexpr" ("condition") {"
                  }
                  # Create block of code inside conditional expression
                  for (j=1; j<=NLCB[i]; j++) {
                     $0=lik_code_block[i,j]
                     if ($0=="#PREDICTION") $0=predrec
                     amt_reference()
                     sub(/^ */,"",$0)
                     model_obj[++nmodelobj]="         "$0
                  }
                  if (preddist!="") model_obj[++nmodelobj]="#      "preddist
                  if (useifelse) model_obj[++nmodelobj]="      }"
               }
#               if (!gotlocation) { model_location[nmodelobj]="$ERROR"; gotlocation=1}
            }
         }
#         hasy=0
#      } else { # nliketype was incremented by "if" but no "Y" found
#         nliketype--
#      }

      $0=savezero
}

function task_object(   i,j) {

   if (!(nestline || nsimline)) return
   ntaskobj=0
   task_obj[++ntaskobj]=mdlname"_task = " TSK_OBJ objstart""
   if (ishelp) {
      task_obj[++ntaskobj]="### Task Properties object"
      task_obj[++ntaskobj]="# Modelling tasks often have many options which the user may specify."
      task_obj[++ntaskobj]="# The task properties object provides a way to collect together options for commonly performed tasks."
   }
   task_obj[++ntaskobj]=""

if (1==0) {
#print task_obj[++ntaskobj]="nliblist="nliblist
   for (i=1; i<=nliblist; i++) {
      ok=split(LIBLIST[i],LIBARGS,"=")
      libarg=LIBARGS[2]
      j=index(libarg,"(")
      libarg=substr(libarg,1,j-1)
#print task_obj[++ntaskobj]="j="j" libarg="libarg" model="advan_num" trans="tran_num" ncmt="ncmt
#      if (libarg=="nmadvan") task_obj[++ntaskobj]="IMPORT{nmadvan = list(target = NMTRAN_CODE, name=\"ADVAN\", param=list(model=0,trans=0, ncmt=0))}"
      #; LIBLIST bolus1=PK(model=Bolus1,param=list(CL,V,DOSE,C))
#      if (libarg=="MYPK") task_obj[++ntaskobj]="IMPORT{MYPK = list(target = MY_LIBRARY, name=\"MYPK\", param=list(model=Bolus1,param=list(CL,V,DOSE,C)))}"
      #; LIBLIST emaximm=PKPD(model=EmaxImmediate,param=list(EMAX,C50,C,E))
#      if (libarg=="MYPKPD") task_obj[++ntaskobj]="IMPORT{MYPKPD = list(target = MY_LIBRARY, name=\"MYPKPD\", param=list(model=EmaxImmediate,param=list(EMAX,C50,C,E)))}"
   }
}



   if (usefulltask==1 && table_options!="") {
      if (isnmtran) {
         task_obj[++ntaskobj]="   ## TABLE"
         task_obj[++ntaskobj]="   #"table_options
         task_obj[++ntaskobj]=""
      }
   }
   if (nestline) {

#      if (ishelp) task_obj[++ntaskobj]="      # estimate (more options will typically be needed)"
      if (usefulltask) {
         if (usefulltask==1) {
            task_obj[++ntaskobj]="   "TSK_TASK " {"
            task_obj[++ntaskobj]=""
            task_obj[++ntaskobj]="      "TSK_EST " {"
         } else {
            MDL_TARGET=TSK_EST
         }
   
         if (usefulltask==1) {
            task_obj[++ntaskobj]="         method" listconnect listname liststart "\"" methopt "\"" listend
         }

         # target code for ESTIMATE
         task_obj[++ntaskobj]="### start TARGET_CODE for "TSK_EST

         open_target(MDL_NMTRAN,"$ESTIMATION","")
         for (i=1; i<=nestline; i++) {
            $0=est_code[i]
            target_code[++ntargetcode]=NMTRAN_VERBATIM_CHAR "EST " $0
         }
         close_target("task")

         if (usefulltask==1) {

            if (MLX_omega_type=="") MLX_omega_type="yes" # default for NM-TRAN is variance
            open_target(MDL_MLXTRAN,"globalSettings","")
               target_code[++ntargetcode]=NMTRAN_VERBATIM_CHAR "EST    globalSettings={"
               target_code[++ntargetcode]=NMTRAN_VERBATIM_CHAR "EST      withVariance="MLX_omega_type","
               target_code[++ntargetcode]=NMTRAN_VERBATIM_CHAR "EST      settingsGraphics=\"%MLXPROJECT%/graphics.xmlx\","
               target_code[++ntargetcode]=NMTRAN_VERBATIM_CHAR "EST      settingsAlgorithms=\"%MLXPROJECT%/algorithms.xmlx\","
               target_code[++ntargetcode]=NMTRAN_VERBATIM_CHAR "EST      resultFolder=\"%MLXPROJECT%/results\"},"
            close_target("task")

            open_target(MDL_MLXTRAN,"estimatePopulationParameters",", after=true")
               target_code[++ntargetcode]=NMTRAN_VERBATIM_CHAR "EST   estimateIndividualParameters( method={conditionalMode} ),"
               target_code[++ntargetcode]=NMTRAN_VERBATIM_CHAR "EST   displayGraphics(),"
            close_target("task")
            
            task_obj[++ntaskobj]="### end TARGET_CODE for "TSK_EST

            if (showblockend) task_obj[++ntaskobj]="      "blockend"# end "TSK_EST

            task_obj[++ntaskobj]=""
            task_obj[++ntaskobj]="      "TSK_LIKE " {"
            task_obj[++ntaskobj]="         method" listconnect listname liststart "\"" "standard" "\"" listend

            task_obj[++ntaskobj]="### start TARGET_CODE for "TSK_LIKE
            open_target(MDL_MLXTRAN,"estimatePopulationParameters",", after=true")
               target_code[++ntargetcode]=NMTRAN_VERBATIM_CHAR "EST    estimateLogLikelihood(method={linearization } ),"
            close_target("task")
            task_obj[++ntaskobj]="### end TARGET_CODE for "TSK_LIKE
            
            if (showblockend) task_obj[++ntaskobj]="      "blockend"# end "TSK_LIKE

            if (ncovline) { 
               task_obj[++ntaskobj]=""
               task_obj[++ntaskobj]="      "TSK_COV " {"
               task_obj[++ntaskobj]="         method" listconnect listname liststart "\"" "standard" "\"" listend

               open_target(MDL_NMTRAN,"$COVARIANCE","")
               task_obj[++ntaskobj]="### start TARGET_CODE for "TSK_COV
               # target code for COVARIANCE
               for (i=1; i<=ncovline; i++) {
                  $0=cov_code[i]
                  target_code[++ntargetcode]=NMTRAN_VERBATIM_CHAR "COV " $0
               }
               close_target("task")

               open_target(MDL_MLXTRAN,"estimatePopulationParameters",", after=true")
                  target_code[++ntargetcode]=NMTRAN_VERBATIM_CHAR "EST    estimateFisherInformationMatrix( method={linearization} ),"
               close_target("task")

               task_obj[++ntaskobj]="### end TARGET_CODE for "TSK_COV

               if (showblockend) task_obj[++ntaskobj]="      "blockend"# end "TSK_COV
               task_obj[++ntaskobj]="   }# end of "TSK_TASK
            } # isfulltask==1
         } # isfulltask != 0

      } else { 
         # not usefulltask
         offset="   "
         task_obj[++ntaskobj]=offset TSK_EST blockstart

#This is handled by the DDMoRe R Package. If the target tool suppors the algorithm it's used, if not then a default is used.
#   saem, foce, fo, focei
#Stuart Moodie 5 Nov 2015

         if (match(methopt,/saem *$/))
            methalgo="saem"
         else if (match(methopt,/ZERO *$/))
            methalgo="fo"
         else if (match(methopt,/FOCE *$/))
            methalgo="foce"
         else if (match(methopt,/FOCE *INTERACTION *$/)  || match(methopt,/FOCE *INTER *$/))
            methalgo="focei"
         else
            methalgo=methopt
         task_obj[++ntaskobj]=offset offset "set algo is "methalgo
         if ((ndeqline || advan_num==10)) { 
            # task_obj[++ntaskobj]=offset offset "TOL="tol_num
         }
         task_obj[++ntaskobj]=offset blockend "# end "TSK_EST" sub block"
      } # end not usefulltask

      if (neditline) {
         task_obj[++ntaskobj]=""
         editcode()
      }

   }

   if (usefulltask==1) {
      if (nsimline) {

         task_obj[++ntaskobj]="      "TSK_SIM " {"
         task_obj[++ntaskobj]="         method" listconnect listname liststart "levels" listconnect listname liststart "DV,ID" listend ", replications=1" listend
         task_obj[++ntaskobj]=""

         task_obj[++ntaskobj]="### start TARGET_CODE for "TSK_SIM
         open_target(MDL_NMTRAN,"$SIMULATION","")
         for (i=1; i<=nsimline; i++) {
            $0=sim_code[i]
            #translate()
            target_code[++ntargetcode]=NMTRAN_VERBATIM_CHAR "SIM " $0
         }
         close_target("task")
         task_obj[++ntaskobj]="### end TARGET_CODE for "TSK_SIM

         task_obj[++ntaskobj]=""

         if (showblockend) task_obj[++ntaskobj]="      "blockend"# end "TSK_SIM
         task_obj[++ntaskobj]="   }# end of "task_sim
      }

      if (ndeqline || advan_num==10) { 
         task_obj[++ntaskobj]=""
         task_obj[++ntaskobj]="      "TSK_SOLVER blockstart
         if (advan_num==6)
            solver_method="\"" "Runge_Kutta" "\""
         else if (advan_num==8)
            solver_method="\"" "Gear" "\""
         else if (advan_num==9)
            solver_method="\"" "LSODE_AES" "\""
         else if (advan_num==10)
            solver_method="\"" "MOM1" "\""
         else if (advan_num==13)
            solver_method="\"" "LSODE" "\""
         
         task_obj[++ntaskobj]="         method" listconnect listname liststart "type=" solver_method listend " # ADVAN"advan_num
         task_obj[++ntaskobj]="         tolrel="tol_num
         if (showblockend) task_obj[++ntaskobj]="      "blockend"# end "TSK_SOLVER
      }

      if (nproline || nabbline || (ntabline && istablecode)) {
         task_obj[++ntaskobj]=""
         task_obj[++ntaskobj]="      "TSK_TARGET blockstart
         task_obj[++ntaskobj]=""

         if (nproline && usefulltask==1) {
            open_target(MDL_NMTRAN,"$PROBLEM",", first=true")
            for (i=1; i<=nproline; i++) {
               $0=pro_code[i]
      #         if (index(toupper($1),"$PRO")==1) $1=";" # Replace $PROBLEM with comment
               #translate()
               target_code[++ntargetcode]=NMTRAN_VERBATIM_CHAR "PROB " $0
            }
            close_target("task")
            task_obj[++ntaskobj]=""
         }
         if (nabbline) {
            open_target(MDL_NMTRAN,"$ABBREVIATED")
            for (i=1; i<=nabbline; i++) {
               $0=abb_code[i]
      #         if (index(toupper($1),"$ABB")==1) $1="" # Remove $ABBREVIATED 
               #translate()
               target_code[++ntargetcode]=NMTRAN_VERBATIM_CHAR "ABBR " $0
            }
            close_target("task")
            task_obj[++ntaskobj]=""
         }

         if (ntabline && istablecode) {
            open_target(MDL_NMTRAN,"$TABLE",",last=true")
            for (i=1; i<=ntabline; i++) {
               $0=tab_code[i]
               if (i==1) $0="$TABLE "$0
               target_code[++ntargetcode]=NMTRAN_VERBATIM_CHAR "TABL " $0
            }
            close_target("task")
         }
         if (showblockend) task_obj[++ntaskobj]="      "blockend"# end "TSK_TARGET
      }
      task_obj[++ntaskobj]=""

   } # end usefulltask

   task_obj[++ntaskobj]=""
   task_obj[++ntaskobj]=objend"# end of task object"


   for (i=1; i<=ntaskobj; i++) {
      $0=task_obj[i]
      if (index($1,NMTRAN_VERBATIM_CHAR)==1 && length($1)>1) { $1=""; sub(/ */,"") }
      if (ismdl5)
         MDLTASK6[++nmdltask6]=$0
      else
         print $0
   }
}

function tel_object() {

   if (usetelobj) {

      tel_obj[++ntelobj]=""
      tel_obj[++ntelobj]=mdlname"_tel = "TEL_OBJ objstart""
      if (ishelp) {
         tel_obj[++ntelobj]="## The TEL is used to specify the sequence of execution of tasks." 
         tel_obj[++ntelobj]="# Result objects are returned by each tel statement which may be used by subsequent tasks."
         tel_obj[++ntelobj]=""
      }
      
      if (neditline) {
      # DATA and PARAMETER editing code
         editcode()
         tel_obj[++ntelobj]=""
      }

      tel_obj[++ntelobj]=""
      if (ishelp) {
         tel_obj[++ntelobj]="# Create Modelling Object Group"
      }
      tel_obj[++ntelobj]=mdlname"_mog=mog(model="mdlname"_mdl, parameter="mdlname"_par, data="mdlname"_dat, tel="mdlname"_tel)"
      tel_obj[++ntelobj]=""
      if (methopt!="") { # Estimate 
         if (ishelp) {
            tel_obj[++ntelobj]="# Fit model using "target_app
         }
         tel_obj[++ntelobj]=mdlname"_fit=estimate(mog="mdlname"_mog, target="target_app", subfolder="mdlname")"
         tel_obj[++ntelobj]=""
         if (ishelp) tel_obj[++ntelobj]="# Update parameter estimates with final estimates"
         tel_obj[++ntelobj]=mdlname"_par=update("mdlname"_fit,"mdlname"_par)"
      }
      if (nsimline) { # Simulate
         if (ishelp) {
            tel_obj[++ntelobj]="# Simulate model using "target_app
         }
         tel_obj[++ntelobj]=mdlname"_fit=simulate(mog="mdlname"_mog, target="target_app", subfolder="mdlname")"
      }
      tel_obj[++ntelobj]=""
      tel_obj[++ntelobj]=objend" # end of "TEL_OBJ
   } else {    # usetelobj=0 so make mog
#You can specify from what file the object should be taken: (Natallia 20 Apr 2015)
#warfarin_PK_BOV_mog=mogobj{
#OBJECTS{
#warfarin_PK_BOV_mdl from file "warfarin_PK_BOV.mdl"
#warfarin_PK_BOV_par
#warfarin_PK_BOV_dat
#warfarin_PK_BOV_task from file "warfarin_PK_BOV.mdl"
#}
         tel_obj[++ntelobj]=""
         tel_obj[++ntelobj]="#Modelling object group"
         tel_obj[++ntelobj]=""
         tel_obj[++ntelobj]=mdlname"_mog="MOG_OBJ"{"

         tel_obj[++ntelobj]="\t"MOG_OBJECTS blockstart
         tel_obj[++ntelobj]="\t\t"mdlname"_mdl" listconnect liststart " type is "MDL_OBJ" " listend
         tel_obj[++ntelobj]="\t\t"mdlname"_dat" listconnect liststart " type is "DAT_OBJ" " listend
         tel_obj[++ntelobj]="\t\t"mdlname"_par" listconnect liststart " type is "PAR_OBJ" " listend
         tel_obj[++ntelobj]="\t\t"mdlname"_task" listconnect liststart " type is "TSK_OBJ" " listend
         tel_obj[++ntelobj]="\t"blockend

if (1==0) {
         tel_obj[++ntelobj]="\t"MOG_MAPPING blockstart
         tel_obj[++ntelobj]="\t\tmObj.AMT=dObj.AMT" blockstart
         tel_obj[++ntelobj]="\t"blockend
}
         tel_obj[++ntelobj]=blockend

   }

      for (i=1; i<=ntelobj; i++) {
         $0=tel_obj[i]
   #      if (index($1,NMTRAN_VERBATIM_CHAR)==1 && length($1)>1) { $1=""; sub(/ */,"") }
         if (ismdl5)
            MDLTEL6[++nmdltel6]=$0
         else
            print $0
      }

}

function executecmd() {
#   if (!outputexecute) {
#      task_obj[++ntaskobj]="# "TSK_LIKE" not yet implemented in MDL Editor"
#      return
#   }

   task_obj[++ntaskobj]="      "TSK_LIKE blockstart

   if (target_app=="NMTRAN_CODE") {
      if (target_cmd=="NMFE")
         taskcmd="call nmfe "mdlname".ctl "mdlname".lst"          
      else if (target_cmd=="WFN")
         taskcmd="call nmgo "mdlname
      task_obj[++ntaskobj]="         command=\""taskcmd"\""
   }
   if (showblockend) task_obj[++ntaskobj]="      "blockend"# end "TSK_LIKE
   task_obj[++ntaskobj]=""
}

function rendcode() {

print ""
print "### End of MDL code"
print ""

print "#### The following code is not part of the MDL. It is used when this script is executed in the R environment in order to check the R syntax used in the MDL."

print "mdl_dir = \".\" # Optional path to hold the R output file which lists the syntax checked MDL code."

print "writemdl(\""mdlname"\"," 
print "   c("mdlname"_mdl,"mdlname"_par,"mdlname"_dat,"mdlname"_task,"mdlname"_tel)"
print ")"

print "### End of R syntax checking code"
}

function data_type(isdata,isuse,item,alias,units, var_list,link_list,dv_list,cat_list,gotcatdef,orgitem,j,adm,use,leveltype) {
   orgitem=item; item=toupper(item); leveltype=""; if (isdata) { cat_list="";CATEGORY[item]=""}
   if (item==idvname)
      use="use is idv" # use is covariate may be added with TIME
   else
      use="use is "tolower(item)
   if (item=="OCC"){
      if (item!=idvname) use="use is varLevel"
      if (useallcov) use=use ", use is covariate"
      var_list="type=categorical"
      if (!isdata) {
         if (isleveltype) var_list="type is parameter"
         var_list=var_list ", level="2 leveltype
      }
   } else if (item=="ID") {
      if (useallcov) use=use ", use is covariate"
      var_list=", type=categorical"
      if (!isdata) {
         if (isleveltype) var_list="type is parameter"
         var_list=var_list ", level="2+hasocc
      }
   } else if (item=="TIME") {
      if (useallcov) use=use ", use is covariate"
      var_list="type=continuous"
      if (units=="")
         timeunit="h"
      else
         timeunit=units
      units= ", units=\""timeunit"\""
   } else if (item=="MDV" || item=="EVID"){
      if (item=="MDV") {
       if (useallcov) use=use ", use is covariate"
      } else {
         use="use is ignore"
         if (useallcov) use=use ", use is covariate"
      }
      var_list="type=categorical"
   } else if (item=="CMT" || item=="ADM" ){
      if (!ismdlpred)
         use="use is cmt"
      else
         use=""
      if (useallcov) use=use ", use is covariate"
      link_list=linkcmtmdl(isdata,((isdata && !usemcl5obj) || (!isdata && usemcl5obj)),item)
      CMTNAME=item
      var_list= "type=categorical" link_list
   } else if (item=="AMT"){
      link_list=linkcmtmdl(isdata,((isdata && !usemcl5obj) || (!isdata && usemcl5obj)),item)
      var_list= "type=continuous" link_list
      if (units=="")
         doseunit="mg"
      else
         doseunit=units
      units= ", units=\""doseunit"\""
      if (ismdlpred)
         use="use is covariate"
      else
         if (useallcov) use=use "use is covariate"
   } else if (item=="II"){
      var_list="type=continuous"
      if (timeunit=="") timeunit="h"
      units= ", units=\""timeunit"\""
      if (!ismdlpred)
         use="use is ii"
      else
         use=""
       if (useallcov) use=use ", use is covariate"
   } else if (item=="ADDL"){
      var_list="type=categorical"
      if (!ismdlpred)
         use="use is addl"
      else
         use=""
       if (useallcov) use=use ", use is covariate"
   } else if (item=="SS" || match(item,/SS[1|2]/)==1 ){
      use="use is ss"
      var_list="type=categorical"
      if (!ismdlpred)
         use="use is ss"
      else
         use=""
       if (useallcov) use=use ",use is covariate"
   } else if (item=="RATE"){
      var_list="type=continuous"
      if (!ismdlpred)
         use="use is rate"
      else
         use=""
       if (useallcov) use=use ", use is covariate"
   } else if (item=="DVID" || item=="YTYPE" || item=="ITYPE"){
      item="DVID"
      use="use is dvid"
      if (useallcov) use=use ",use is covariate"
      var_list="type=categorical"
       jcatdef=0
       for (j=1; j<=ncatdef; j++) {
          if (CATDEF[j,1]==toupper(item)) jcatdef=j
       }
       if (!jcatdef) {
         CATDEF[++ncatdef,1]=toupper(item)
         CATDEF[ncatdef,2]="dosing,conc,PCA"
         jcatdef=ncatdef
      }
      isconcPCA=(mdlname=="warfarin_pkpd_turnover" || index(mdlname,"UC003")) 
      if (isdata) { 
         if (usedvidcat && isconcPCA && iscatdefined) {
            var_list=var_list "("CATDEF[ncatdef,2]"), define=[{category=dosing,value=0},{category=conc,value=1},{category=PCA,value=2}]"
            cat_list=" withCategories {dosing when 0, conc when 1, PCA when 2}"
            #data_declare=data_declare "dosing conc PCA"
         }
      } else {
         if (usedvidcat && isconcPCA && iscatdefined) {
             var_list=var_list "("CATDEF[jcatdef,2]")"
             CATEGORY[toupper(item)]=CATDEF[jcatdef,2]
         }
      } 
   } else if (item=="STUDY" || item=="STDY") {
       use="use is covariate"
       var_list="type=categorical"
       if (isleveltype) var_list="type is parameter"
       if (!isdata) var_list=var_list ", level="3+hasocc
   } else if (item=="DV") {
       use="use is dv"
       if (useallcov) use=use ", use is covariate"
       var_list="type=continuous"
       if (!isdata) {
          if (isleveltype) var_list="type is observation"
          var_list=var_list ", level=1"
       } else {
          dv_list=", "listdvmdl()
          var_list=var_list dv_list
       }
   } else if (item=="SEX" || item=="M1F0" || item=="M0F1" || item=="GEN" || item=="GEND" ) {
       use="use is catCov"
       var_list="type=categorical"
       jcatdef=0
       for (j=1; j<=ncatdef; j++) {
          if (CATDEF[j,1]==toupper(item)) jcatdef=j
       }
       if (!jcatdef) {
          CATDEF[++ncatdef,1]=toupper(item)
          CATDEF[ncatdef,2]="female,male,MISSING"
          jcatef=ncatdef
       }
       if (isdata) { 
          if (iscatdefined) {
            var_list=var_list "("CATDEF[ncatdef,2]"), define=[{category=female,value=0},{category=male,value=1},{category=MISSING,value=99}]"
            #data_declare=data_declare "female male"
            cat_list=" withCategories {female when 0, male when 1, MISSING when 99}"
          }
       } else {
          if (iscatdefined) {
             var_list=var_list "("CATDEF[jcatdef,2]")"
             CATEGORY[toupper(item)]=CATDEF[jcatdef,2]
          }
       }
   } else if (item=="RACE") {
       use="use is catCov"
       var_list="type=categorical"
       jcatdef=0
       for (j=1; j<=ncatdef; j++) {
          if (CATDEF[j,1]==toupper(item)) jcatdef=1
       }
       if (!jcatdef) {
          CATDEF[++ncatdef,1]=toupper(item)
          CATDEF[ncatdef,2]="white,black,other"
          jcatdef=ncatdef
       }
       if (isdata) {
         if (iscatdefined) {
           var_list=var_list "(white,black,other), define=[{category=white,value=1},{category=black,value=2},{category=other,value=seq(3,10,1)}]"
           #data_declare=data_declare "white black other"
           cat_list=" withCategories {white when 1, black when 2, other when seq(3,10,1)}"
         }

       } else {
          if (iscatdefined) {
             var_list=var_list "("CATDEF[jcatdef,2]")"
             CATEGORY[toupper(item)]=CATDEF[jcatdef,2]
          }

       }
   } else if (index(item,"WT")==1 || index(item,"BW")==1){
       use="use is covariate"
       if (item!=idvname) use="use is covariate"
       var_list="type=continuous"
       units= ", units=\"kg\""
   } else if (index(item,"AGE")==1 || index(item,"PNA")==1){
       use="use is covariate"
       var_list="type=continuous"
       if (item=="AGE" || item=="PNAY" || item=="PNA") 
         units="y" 
       else if (item=="PNAW")
         units="week"
       units= ", units=\""units"\""
   } else {
       if (item==idvname) {
          if (useallcov) use=use ",use is covariate"
       } else
          use="use is covariate"
       var_list="type=continuous"
   }
   if (isdata) var_list=", "var_list
   if (usevarlist) {
      if (isuse) var_list=use var_list
   } else {
      if (isdata) var_list=use
    }
   if (useunits) {
      if (units!="" && !match(units,/, *units=/)) units=", units=\""units"\""
   } else {
      units=""
   }
#print "data_type: useunits="useunits" units="units
   return item listconnect listname liststart var_list link_list dv_list cat_list units listend
}

function getYDVID( ydvid,i,dvid, hasdvid, saverec) {
   saverec=$0
   i=index($0,";")
   if (i) $0=substr($0,1,i-1)
#print "getYDVID: ydvid="ydvid" $0="$0
   if (match($0,/^ *IF *\(/)) {
      ydvid=substr($0,")"+1)
      ydvid=substr(ydvid, index(ydvid,"=")+1)
   } else {
      ydvid=substr($0, index($0,"=")+1)
   }
   $0=saverec
   i=match(ydvid,/[\*|\/|\+|\-]/)
   if (i) {
      ydvid=substr(ydvid,1,i-1)
   }
   gsub(/ /,"",ydvid)
   ++ndvid
   if (wasflag) { # F_FLAG preceding the "Y=" record
      ydvid="LIKE"ndvid
      wasflag=0
   }
   gsub(/\(/,"_",ydvid)
   gsub(/\)/,"_",ydvid)

   ydvid=ydvid"_obs"
   #ydvid="Y"ndvid"_obs"
   # Ignore duplicate names (used to generate Bernouilli Product 4 example)
   hasdvid=0
   for (i=1; i<=ndvid; i++) {
      if (YDVID[i]==ydvid) hasdvid=1      
   }
   if (hasdvid) { --ndvid; return }

   YDVID[ndvid]=ydvid
#   lastDVIDrec=""
   if (lastDVIDrec=="") {
   dvid=ndvid
   } else {
   dvid=lastDVIDrec
   gsub(/ /,"",dvid)
   i=match($0,/DVID\./)
   dvid=substr(dvid,i+5)
   i=index(dvid,".")
   dvid=substr(dvid,i+1)
   i=index(dvid,".")
   dvid=substr(dvid,i+1)
   i=index(dvid,")")
   dvid=substr(dvid,1,i-1)
   lastDVIDrec=""
#print "dvid="dvid
#   dvid=ndvid
   }
   NDVID[ndvid]=dvid
   data_declare=data_declare ydvid" "
#print "getYDVID: ndvid="ndvid" ydvid="ydvid" $0="$0
}

function listdvmdl(  i,predrec,listdv) {
  if (ndvid<=1) {
     if (YDVID[1]=="Y1_obs") {
        YDVID[1]="Y"
        sub(/Y1_obs/,"Y",data_declare)
     }
     return "variable="YDVID[1]
  }
  listdv="define={"
   for (i=1; i<=ndvid; i++) {
#      listdv=listdv "{pred="YDVID[i]",predId="NDVID[i]"}"
      listdv=listdv NDVID[i] " in "DVIDNAME" as "YDVID[i]
#      listdv=listdv "{pred="YDVID[i]",predid="i"}"
      if (i!=ndvid) listdv=listdv ","
   }
   return listdv "}"
}
function linkcmtmdl(isdata,isuse,item, litem,adm,link_declare) {
   litem=tolower(item)
   if (!iscmtlink && !isdata) {
      adm=""
      if (litem=="amt") {
         if (ncmt) {
            adm= ", administration="CMT[1]
            link_declare=link_declare ", "CMT[1]
         }
      }
      return adm
   }
   if (isuse) {
# define = {1 in CMT as INPUT_KA}
      if (ncmt && advan_num) {
         if (hascmt || hasadm) {
            adm="define={"
            for (j=1; j<=ncmt; j++) {
               if (j<ncmt)
                  admdelim=","
               else
                  admdelim="}"
#               adm=adm "{modelCmt="CMT[j]",dataCmt="j"}" admdelim
               adm=adm j" in "CMTNAME" as "CMT[j] admdelim
               link_declare=link_declare " "CMT[j]
            }
         } else {
            if (hascmt || hasadm) {
               adm="define={1 in "CMTNAME" as "CMT[1] "}"
            } else {
               adm="variable="CMT[1]
            }
            link_declare=link_declare " "CMT[1]
         }
      }
      if (adm!="") adm= ", " adm
      if (iscmtlink2cmt) {
         if (litem!="cmt")
            adm=""
         else
            data_declare=data_declare link_declare
      } else {
         if (litem!="amt")
            adm=""
         else
            data_declare=data_declare link_declare
      }
   }
   return adm
}

function editcode( i) {

   for (i=1; i<=neditline; i++) {
      task_obj[++ntaskobj]="#"edit_code[i]
   }
}

function whencmt() {
  if (!hascmt) return ""
  return "" # AMT and CMT are linked in use=cmt variable list
  return " when (CMT=="idepcmt");"
}

function mdlvarcode( i,item,comment,isdata,mdlvar,model_idv) {
   for (i=1; i<=ninput; i++) {
#print "DROP["i"]="DROP[i] " INPUT["i"]="INPUT[i]" ALIAS["i"]="ALIAS[i]
#      if (toupper(INPUT[i+1])!="DROP") {
      if (!DROP[i]) { 
         item=INPUT[i]
         sub(/ *$/,"",item) # remove trailing blanks
         if (item!="") {
            if (j=index(item,";")) {
               comment="#"substr(item,j+1)
               item=substr(item,j-1)
            } else {
               comment=""
            }
            isdata=0
            mdlvar=data_type(isdata,usemcl5obj,item,ALIAS[i],DATUNITS[i]) comment
            if (usemcl5obj) {
               model_obj[++nmodelobj]="      "mdlvar
            } else {
               if (mdlvarcovariates && index(mdlvar,"level=")==0) {
                 if (ndeqline) {
                    model_idv="#IDV#"  # Allow TIME to appear in COVARIATES block
                    idvname="T"
                 } else {
                    model_idv=idvname
                 }
#                  usemdlvar=!(index(mdlvar,model_idv listconnect) || index(mdlvar,"AMT"listconnect) || index(mdlvar,"RATE"listconnect) || index(mdlvar,"CMT"listconnect) || index(mdlvar,"SS"listconnect) || index(mdlvar,"ADDL"listconnect) || index(mdlvar,"II"listconnect) || index(mdlvar,"MDV"listconnect) || index(mdlvar,"EVID"listconnect) || index(mdlvar,"DVID"listconnect))
                  usemdlvar=!(index(mdlvar,model_idv listconnect) || index(mdlvar,"SS"listconnect) || index(mdlvar,"ADDL"listconnect) || index(mdlvar,"II"listconnect) || index(mdlvar,"EVID"listconnect)) 
                  if (!useallcov && (index(mdlvar,"AMT"listconnect)==1 || index(mdlvar,"MDV"listconnect)==1 || index(mdlvar,"DV"listconnect)==1 || index(mdlvar,"DVID"listconnect)==1  || (index(mdlvar,"TIME"listconnect)==1 && idvname=="T"))) usemdlvar=0
#model_obj[++nmodelobj]="mdlvarcode: usemdlvar="usemdlvar " ndeqline="ndeqline" ismdlpred="ismdlpred" mdlvar=|" mdlvar "| idvname=|"idvname "| model_idv="model_idv " index="index(mdlvar,idvname listconnect) " indext="index(mdlvar,"TIME" listconnect)
                  if ((ismdlpred || usemdlvar) && index(mdlvar,idvname listconnect)!=1 && index(mdlvar,"MDV"listconnect)!=1 && index(mdlvar,"DVID"listconnect)!=1 && index(mdlvar,"RATE"listconnect)!=1 && index(mdlvar,"ADDL"listconnect)!=1 && index(mdlvar,"SS"listconnect)!=1 && index(mdlvar,"II"listconnect)!=1 && index(mdlvar,"EVID"listconnect)!=1 && index(mdlvar,"CMT"listconnect)!=1) {
#model_obj[++nmodelobj]="mdlvarcode: ismdlpred="ismdlpred" usemdlvar="usemdlvar" model_idv="model_idv" ndeqline="ndeqline
                    if (!usevarlist) {
                       mdlvar=substr(mdlvar,1,index(mdlvar,listconnect)-1)
#model_obj[++nmodelobj]="mdlvarcode:      "mdlvar " CATEGORY["toupper(mdlvar)"]="CATEGORY[toupper(mdlvar)]
                       if (CATEGORY[toupper(mdlvar)]!="") mdlvar=mdlvar " withCategories {" CATEGORY[toupper(mdlvar)]"}"
                    }
                    model_obj[++nmodelobj]="      "mdlvar
                  }
               }
               if (mdlvarlevels && index(mdlvar,"level=")) {
                  model_obj[++nmodelobj]="      "mdlvar
               }
            }
         }
      }
   }
}

function predarg(line, i,j,k,code,var,lhs,saverec) {
   saverec=$0
   $0=line
   if (index($1,NMTRAN_VERBATIM_CHAR) || index($1,"(")==1) {
       $0=saverec
       return # Do not search verbatim code or TARGET_CODE like (CALLFL=-1)
   }
   $0=saverec
   for (i=1; i<=ncmt; i++) {
       replace("  ","A("i")") # remove A(i) compartment names
   }
   line=$0
   if (i=index(line,";"))
      code=substr(line,1,i-1)
   else
      code=line
   cvar=""
   if (match(code,/IF *\(/)) {
      code=logical(code)
      j=index(code,"("); nopen=1
      for (i=j+1; i<length(code); i++) {
         char=substr(code,i,1)
         if (char==")") {
            nopen--
            if (nopen==0) {
               k=i
               i=length(code)
            }
         }
      }
      condition=substr(code,j+1,k-j-1)
      code=substr(code,k+1)
      do {
         j=match(condition,/\&|\||<|>|=|\!/)
         if (j) {
            cvar=cvar","substr(condition,1,j-1)
            condition=substr(condition,j+1)
            if (j=match(condition,/[A-Z]/)) {
               condition=substr(condition,j)
            }
         } else {
            cvar=cvar","condition
         }
      } while (j>0)
   }
   if ((j=index(code,"="))>0) 
      lhs=","substr(code,1,j-1)
   else
      lhs=""
   return cvar lhs
}

function getsuboptnum(line, key,  i,LINE,item,na,num_list) {
   i=index(line,key)
   if (i==0) return ""
   gsub(/,/," ",line) # remove comma delimiter
#print "getsuboptnum key="key" line="line" num_list="num_list
   if(i=index(line,";")) line=substr(line,1,i-1)
   lenkey=length(key)
   sub(key,key " ",line) # e.g. ADVAN2 -> ADVAN 2 
   gsub(/=/," ",line) # remove any "="
   num_list=""
   na=split(line,LINE)
   for (i=1; i<=na; i++) {
      item=LINE[i]
      if (index(item,key)) {
         len=length(item)
         if (len>length(key))
             num=substr(item,lenkey+1,len-lenkey)+0
         else
             num=LINE[i+1]+0
         if (num) num_list=num_list num ","
      }
   }
   sub(/,$/,"",num_list)
   return num_list
}

function getparlist(advan_num,tran_num,comp_num,nk,KEYS,CMTNUM, i,j,n,NUM,key,list) {
   if (advan_num==1 || advan_num==2) {
      if (tran_num==1) {
         par_list="V,K"
      } else if (tran_num==2) {
         par_list="V,CL"
      }
      if (advan_num==2)  {
         par_list=par_list",KA"
         ncmt=2
      } else
         ncmt=1

  } else if (advan_num==3) {
      if (tran_num==1) {
         par_list="V,K,K12,K21"
      } else if (tran_num==3) {
         par_list="CL,V,Q,VSS"
      } else if (tran_num==4) {
         par_list="CL,V1,Q,V2"
      } else if (tran_num==5) {
         par_list="AOB,ALPHA,BETA"
      } else if (tran_num==6) {
         par_list="ALPHA,BETA,K21"
      }
      ncmt=2
  } else if (advan_num==4) {
      if (tran_num==1) {
         par_list="V,K,K23,K32,KA"
      } else if (tran_num==3) {
         par_list="CL,V,Q,VSS,KA"
      } else if (tran_num==4) {
         par_list="CL,V2,Q,V3,KA"
      } else if (tran_num==5) {
         par_list="AOB,ALPHA,BETA,KA"
      } else if (tran_num==6) {
         par_list="ALPHA,BETA,K32,KA"
      }
      ncmt=3
  } else if (advan_num==5 || advan_num==7) {
      par_list=""
      for (i=1; i<=comp_num; i++) {
         par_list=par_list"K"i"0,"
         for (j=1; j<=comp_num; j++) {
            if (i!=j) par_list=par_list"K"i j","
         }
      }
      sub(/,$/,"",par_list)
  } else if (advan_num==10) {
      par_list="V,VM,KM"
  } else if (advan_num==11) {
      if (tran_num==1) {
         par_list="V,K,K12,K21,K13,K31"
      } else if (tran_num==4) {
         par_list="CL,V1,Q2,V2,Q3,V3"
      } else if (tran_num==6) {
         par_list="ALPHA,BETA,GAMMA,K21,K31"
      }
      ncmt=3
  } else if (advan_num==12) {
      if (tran_num==1) {
         par_list="V,K,K23,K32,K24,K42,KA"
      } else if (tran_num==4) {
         par_list="CL,V2,Q3,V3,Q4,V4,KA"
      } else if (tran_num==6) {
         par_list="ALPHA,BETA,GAMMA,K32,K42,KA"
      }
      ncmt=4
  } else { # advan 6,8,9,13
     par_list=""
  }
  for (i=1; i<=nk; i++) {
     key=KEYS[i]
     list=CMTNUM[i]
     n=split(list,NUM,",")
     for (j=1;j<=n; j++) {
        par_list=par_list","key NUM[j]
     }
  }
  if (CMTNUM[1]=="") { # Scale factor
     if (advan_num==1 || advan_num==3 || advan_num==11) {
        par_list=par_list",S1"
     } else if (advan_num==2 || advan_num==4 || advan_num==12) {
        par_list=par_list",S2"
     }
  }
  sub(/^,/,"",par_list)
  sub(/,$/,"",par_list)
  if (index(predargs,",FO,")) par_list=par_list ",FO" 
  if (index(predargs,",F0,")) par_list=par_list ",F0" 
  return par_list
}

function mdlpred6( j,k,islibrary) {
   for (j=MDL5START[MDL_PRED]; j<=MDL5FINISH[MDL_PRED]; j++) {
       $0=MDL5[j]
       gsub(/\t/,"   ")
       if (match($0,/^ *ODE/)) {
          sub(/ODE/,"DEQ")
       }
       if (match($0,/ODE *$/)) {
          sub(/ODE *$/,"DEQ")
       }

       islibrary=(j>=MDL5START[MDL_LIB] && j<=MDL5FINISH[MDL_LIB])
       if (islibrary) {
          if (match($0,/= *nmadvan *\(/)) {
             line=substr($0,index($0,"(")+1)
             gsub(/ /,"",line)
             natt=split(line,ATTRIBS,",")
             for (k=1; k<=natt; k++) {
                split(ATTRIBS[k],VALUEPAIR,"=")
                if (VALUEPAIR[1]=="model") 
                   advan_num=VALUEPAIR[2]
                else if (VALUEPAIR[1]=="tran") 
                   tran_num=VALUEPAIR[2]
                else if (VALUEPAIR[1]=="ncmt") 
                   ncmt=VALUEPAIR[2]
             }
   #print "advan_num="advan_num" tran_num="tran_num" ncmt="ncmt
   #advan_num=1; tran_num=1; 

            if (!(advan_num==6 || advan_num==8 || advan_num==9 || advan_num==13)) {
               # Get PREDPP parameter names
               par_list=getparlist(advan_num,tran_num,ncmt,nk,KEYS,CMTNUM) "," par5_list
               MDLPRED6[++nmdlpred6]=MDL5[MDL5START[MDL_LIB]]
               nocmtequiv=(advan_num==5 || advan_num==7 ||  advan_num==10  || tran_num==3 || tran_num==6)
               showlib=(!(iscmtido || iscompartment) || nocmtequiv)

               if (showlib) {
                  nlibsubblock=0
                  library_subblock()
                  for (k=1; k<=nlibsubblock; k++) {
                     MDLPRED6[++nmdlpred6]=lib_subblock[k]
                  }
               } else if (iscmtido) {
                  ncmtsubblock=0
                  cmt_ido_subblock()
                  for (k=1; k<=ncmtsubblock; k++) {
                     MDLPRED6[++nmdlpred6]=cmt_subblock[k]
                  }
               } else if (iscompartment) {
                  ncmtsubblock=0
                  compartment_subblock()
                  for (k=1; k<=ncmtsubblock; k++) {
                     MDLPRED6[++nmdlpred6]=cmt_subblock[k]
                  }
               } else {
                  print "mdlpred6 error: not iscmtido and not is macro"
               }
               # Show alternative version of LIB or CMT or MACRO
               if (showlib) {
                  ncmtsubblock=0
                  if (iscmtido)
                     cmt_ido_subblock()
                  else
                     compartment_subblock()
                  for (k=1; k<=ncmtsubblock; k++) {
                     MDLPRED6[++nmdlpred6]=cmt_subblock[k]
                  }
               } else {
                  nlibsubblock=0
                  library_subblock()
                  for (k=1; k<=nlibsubblock; k++) {
                     MDLPRED6[++nmdlpred6]=lib_subblock[k]
                  }
                  ncmtsubblock=0
                  if (iscompartment)
                     cmt_ido_subblock()
                  else
                     compartment_subblock()
                  for (k=1; k<=ncmtsubblock; k++) {
                     MDLPRED6[++nmdlpred6]=cmt_subblock[k]
                  }
               }

               MDLPRED6[++nmdlpred6]=MDL5[MDL5FINISH[MDL_LIB]]
            }
          }
       } else { # not library
          gsub(/   /,"\t")
          sub(/amount\./,"")
          MDLPRED6[++nmdlpred6]=$0
       }
    }
}

function domdl5() {
   sub(/,$/,"",par5_list)

   updatemdl5lists()

   mdl5blocks()
   mdlpred6()
   mdltask6()
   mdltel6()

   # Data Object
   for (i=MDL5START[DAT_OBJ]; i<=MDL5FINISH[DAT_OBJ]; i++) {
      print MDL5[i]
   }

   print ""
   # Parameter Object
   for (i=MDL5START[PAR_OBJ]; i<=MDL5FINISH[PAR_OBJ]; i++) {
      print MDL5[i]
   }

   print ""
   # Model object before MODEL_PREDICTION
   for (i=MDL5START[MDL_OBJ]; i<MDL5START[MDL_PRED]; i++) {
      print MDL5[i]
   }
   
   # Model object MODEL_PREDICTION
   for (i=1; i<=nmdlpred6; i++) {
      print MDLPRED6[i]
   }

   # Model object after MODEL_PREDICTION
   for (i=MDL5FINISH[MDL_PRED]+1; i<=MDL5FINISH[MDL_OBJ]; i++) {
      print MDL5[i]
   }

   print ""
   # Task Object
   for (i=1; i<=nmdltask6; i++) {
      print MDLTASK6[i]
   }
   print ""
   # TEL object
   for (i=1; i<=nmdltel6; i++) {
      print MDLTEL6[i]
   }
   
} 

function cmt_ido_subblock( ncmt,ncmtline,dist,elim,i,np,par,maxcmt,iscentral,idepot,icentral,idistrib1,idistrib2,idistrib3,elim1,fromdiv,dist1,dist2,ioutput,dist13,dist21,dist31,elseif,comment) {

   return

   if (advan_num==2 || advan_num==4 || advan_num==12) { # depot compartment (ncmt0) is 1 for compatibility with NONMEM default
      ncmt0=1
      isdepot=1
   } else {
      ncmt0=1
      isdepot=0
   }
   idepot=ncmt0
   icentral=idepot+isdepot
   idistrib1=icentral
   idistrib2=icentral+1
   idistrib3=idistrib2+1
   if (isdepot)
      iscentral=", central=true"
   else
      iscentral=""
   if (tran_num==2 || tran_num==3 || tran_num==4) {
      if (advan_num==1 || advan_num==2) {
         elim1="cl=CL"
         if (isdepot) {
            fromdiv="/V"
            dist1="v=V"
         } else {
            fromdiv="/V"
            dist1="v=V"
         }
         ioutput=idistrib1+1
      } else if (advan_num==3 || advan_num==4) {
         elim1="cl=CL"
         if (isdepot) {
            fromdiv="/V2"
            dist1="v=V2"
         } else {
            fromdiv="/V1"
            dist1="v=V1"
         }

         if (tran_num==3) {
            if (isdepot) {
               dist2="v=VSS-V2, cl=Q"
            } else {
               dist2="v=VSS-V1, cl=Q"
            }
         } else {
            if (isdepot) {
               dist2="v=V3, cl=Q"
            } else {
               dist2="v=V2, cl=Q"
            }
         }
         ioutput=idistrib2+1
      } else if (advan_num==11 || advan_num==12) {
         elim1="cl=CL"
         if (isdepot) {
            fromdiv="/V2"
            dist1="v=V2"
            dist2="v=V3, cl=Q3"
            dist3="v=V4, cl=Q4"
         } else {
            fromdiv="/V1"
            dist1="v=V1"
            dist2="v=V2, cl=Q2"
            dist3="v=V3, cl=Q3"
         }
         ioutput=idistrib3+1
      }
   } else { # tran_num=1
      fromdiv=""
      if (advan_num==1 || advan_num==2 || advan_num==10) {
         if (advan_num==10)
            elim1="vmax=VM, km=KM"
         else
            elim1="kout=K"
         dist1="v=V"
         ioutput=idistrib1+1
      } else if (advan_num==3 || advan_num==4) {
         elim1="kout=K"
         if (isdepot) {
            dist1="v=V, kout=K23, to="idistrib2
            dist21="kout=K31"
         } else {
            dist1="v=V, kout=K12, to="idistrib2
            dist21="kout=K21"
         }
         ioutput=idistrib2+1
      } else if (advan_num==11 || advan_num==12) {
         elim1="kout=K"
         if (isdepot) {
            dist1="v=V, kout=K23, to="idistrib2
            dist13="kout=K24, to="idistrib3
            dist21="kout=K32"
            dist31="kout=K42"
         } else {
            dist1="v=V, kout=K12, to="idistrib2
            dist13="kout=K13, to="idistrib3
            dist21="kout=K21"
            dist31="kout=K31"
         }
         ioutput=idistrib3+1
      }
   }

   maxcmt=getmaxcmt()

   ncmtline=0
   ncmt=idepot-1
   if (maxcmt==(idepot-1)) maxcmt=idepot
   comment="#"
   for (i=idepot; i<=maxcmt; i++) {
     idepcmt=i
     #if (hascmt && (maxcmt>idepot) cmt_line[++ncmtline]="  if (CMT=="idepcmt") {"
     if (hasrate) {
        elseif="if"
        if (R_CMT[idepcmt]!= "") {
           cmt_line[++ncmtline]=comment "     "elseif" (RATE==-1) {"
           SUB_CMT[++ncmt]="       input_cmt["idepcmt"]" listconnect listname liststart "from=AMT" whencmt()   R_CMT[idepcmt] LAG_CMT[idepcmt] F_CMT[idepcmt] listend
           cmt_line[++ncmtline]=SUB_CMT[ncmt]
           elseif="} else {"
        }
        if (D_CMT[idepcmt]!= "") {
           cmt_line[++ncmtline]=comment "     "elseif" (RATE==-2) {"
           SUB_CMT[++ncmt]="       input_cmt["idepcmt"]" listconnect listname liststart "from=AMT" whencmt()   D_CMT[idepcmt] LAG_CMT[idepcmt] F_CMT[idepcmt] listend
           cmt_line[++ncmtline]=SUB_CMT[ncmt]
           elseif="} else {"
        }
        if (1==0) { # Not currently allowed
           cmt_line[++ncmtline]=comment "     "elseif" (RATE==-3) {"
           SUB_CMT[++ncmt]="       input_cmt["idepcmt"]" listconnect listname liststart "from=AMT" whencmt()   ", kin=KA" LAG_CMT[idepcmt] F_CMT[idepcmt] listend
           cmt_line[++ncmtline]=SUB_CMT[ncmt]
           elseif="} else {"
        }
        if (elseif=="if") elseif=""
        cmt_line[++ncmtline]=comment "     "elseif
        SUB_CMT[++ncmt]="       input_cmt["idepcmt"]" listconnect listname liststart "from=AMT" whencmt()   LAG_CMT[idepcmt] F_CMT[idepcmt] listend
        cmt_line[++ncmtline]=SUB_CMT[ncmt]
        if (elseif!="") cmt_line[++ncmtline]=comment "     }"
     } else {
        SUB_CMT[++ncmt]="       input_cmt["idepcmt"]" listconnect listname liststart "from=AMT" whencmt()   LAG_CMT[idepcmt] F_CMT[idepcmt] listend
        cmt_line[++ncmtline]=SUB_CMT[ncmt]
#        cmt_line[++ncmtline]="ncmt="ncmt" maxcmt="maxcmt" i="i" idepot="idepot
     }

     if (isdepot && (i==idepot || LAG_CMT[idepcmt]!="" || F_CMT[idepcmt]!="")) {
        SUB_CMT[++ncmt]="    transfer_cmt["icentral"]" listconnect listname liststart "type is compartment, modelCMT="modelCmt listend
        SUB_CMT[++ncmt]="    :" listconnect listname liststart "from=A["idepcmt"], kout=KA" listend
        cmt_line[++ncmtline]=SUB_CMT[ncmt]
     }
     #if (hascmt && maxcmt>idepot) cmt_line[++ncmtline]=comment "  }"
   } # end maxcmt loop

   SUB_CMT[++ncmt]="     distrib_cmt["icentral"]" listconnect listname liststart dist1 iscentral listend
   cmt_line[++ncmtline]=SUB_CMT[ncmt]
   if (advan_num==3 || advan_num==4) {
     if (tran_num==3 || tran_num==4) {
        SUB_CMT[++ncmt]="     distrib_cmt["idistrib2"]" listconnect listname liststart dist2 listend
        cmt_line[++ncmtline]=SUB_CMT[ncmt]
     } else if (tran_num==1) {
#        SUB_CMT[++ncmt]="     distrib_cmt["icentral"]" listconnect listname liststart dist12  listend
#        cmt_line[++ncmtline]=SUB_CMT[ncmt]
        SUB_CMT[++ncmt]="     distrib_cmt["idistrib2"]" listconnect listname liststart dist21 listend
        cmt_line[++ncmtline]=SUB_CMT[ncmt]
     }
   }

   if (advan_num==11 || advan_num==12) {
     if (tran_num==4) {
        SUB_CMT[++ncmt]="     distrib_cmt["idistrib2"]" listconnect listname liststart dist2 listend
        cmt_line[++ncmtline]=SUB_CMT[ncmt]
        SUB_CMT[++ncmt]="     distrib_cmt["idistrib3"]" listconnect listname liststart dist3 listend
        cmt_line[++ncmtline]=SUB_CMT[ncmt]
     } else if (tran_num==1) {
        SUB_CMT[++ncmt]="     distrib_cmt["icentral"]" listconnect listname liststart dist13  listend
        cmt_line[++ncmtline]=SUB_CMT[ncmt]
        SUB_CMT[++ncmt]="     distrib_cmt["idistrib2"]" listconnect listname liststart dist21 listend
        cmt_line[++ncmtline]=SUB_CMT[ncmt]
        SUB_CMT[++ncmt]="     distrib_cmt["idistrib3"]" listconnect listname liststart dist31 listend
        cmt_line[++ncmtline]=SUB_CMT[ncmt]
     }
   }
   if (F_CMT[0]!="") {
      SUB_CMT[++ncmt]="      output_cmt["ioutput"]" listconnect listname liststart "from=A["icentral"]" fromdiv ", "elim1 "*" F_CMT[0] listend
      cmt_line[++ncmtline]=SUB_CMT[ncmt]
   }


   if (showlib) {
         comment="# "
         cmt_subblock[++nidosubblock]=""
         cmt_subblock[++nidosubblock]=comment "   "MDL_IDO blockstart
   } else {
      cmt_subblock[++nidosubblock]="      ##  Parameters = "par_list
      comment=""
   }


   for (i=1; i<=ncmtline; i++) {
      cmt_subblock[++nidosubblock]=comment cmt_line[i]
   }

   if (showblockend) 
      cmt_subblock[++nidosubblock]=comment"   "blockend"# end "MDL_IDO
   else
      cmt_subblock[++nidosubblock]=comment"   END"# "MDL_IDO

   cmt_subblock[++nidosubblock]=comment
   cmt_subblock[++nidosubblock]=comment"      F = A["icentral"]/"Fscale

} # end cmt_ido_subblock


# 19 Mar 2012 version 0 (no version info included)
# 19 Mar 2012 version 0.9 alpha
#  Fixed DROP item bug
# 24 Mar 2012 version 0.95 alpha
#  Additional translation of details for LIKE()
# 18 Apr 2012 version 0.96 alpha
#  Changed SDEXPR to "" when LIKE() using LIKELIHOOD
#  Corrected logical translation of .AND. to & which cut off first char of second variable
#  Extracted $EST record options for task object function
# 19 Apr 2012 version 0.97 alpha
#  Fixed bug in replacement of A() in deriv() expression
#  Code data object FILE as a block
#  Added Random Effect level
# 19 Apr 2012 version 0.98 alpha
#  Fixed OUTPUT bug with multiple tables
# 25 Apr 2012 version 0.99 alpha
#  Add ETA and EPS prefixes for random effects
# 29 Apr 2012 version 0.991 alpha
#  Add NONMEM as target to task object
#  Add Random Variable Definition and Parameter Block to Individual Block
#  Add library call to Prediction Block if ADVAN is used
#  Add list of variables used for Prediction Block which may need to be shared
# 30 Apr 2012 version 0.992 alpha
#  Add idv and tol to ode() list
# 01 May 2012 version 0.993 alpha
#  Tidied up block names
# 03 May 2012 version 0.994 alpha
#  Block names coded as variables, blocks renamed
# 09 May 2012 version 0.995 alpha
#  Model variable lists revised
#  Model selection criteria change to if list format
#  Model LIBRARY subblock added
#  Output conditional on having some input for each object or block
# 16 May 2012 version 0.996 alpha
#  Corrected HEADER output for dropped items
# 25 May 2012 version 0.997 alpha
#  Help comments moved inside blocks
#  log changed to ln
#  levels moved to RHS of random variable definitions
#  endless loop bug in predarg() fixed
# 26 May 2012 version 0.998 alpha
#  fix empty ode() bug
# 25 Aug 2012 version 1.000 beta
#  Corrected used of "expression" when "statement" would be appropriate
#  Corrected block bug with multiple OMEGA blocks
#  Block delimiters are now shown
#  Support for SAME in VARIABILITY
# 22 Sep 2012 version 1.001 beta
#  BLOCK names use _ not blank
#  ETA(n) converted to eta_[PPV_NAME] in $TABLE
# 16 Oct 2012 version 1.002 beta
#  ESTIMATE block curly brackets corrected
#  Block name mispelling corrected
# 29 Nov 2012 version 1.003 beta
#  ADVAN and TRAN in $SUBR recognition improved
#  OUTPUT variables concatenation bug fixed
#  SAME block bug fixed
#  WT now noted as covariate
# 30 Nov 2012 version 1.004 beta
#  Data object DROPped variables have annotation
#  Parameter comments in NM-TRAN retained in MDL parameter object
# 8 Dec 2012 version 1.005 beta
#  'if' removed from IGNORE and ACCEPT statements
#  ')' after hi value removed from parameter value list
#  change ETA() to ETA[] in OUTPUT block
# 9 Feb 2013 version 1.006 beta
#  Variability parameter section distinguishes covariance block, diag block and non-block elements
#  Omega block linked to SAME block by name
#  errorexit() replaces EXIT
#  Variability parameter comment bug fixed
#  $TABLE comments in OUTPUT block bug fixed
# 10 Feb 2013 version 1.007 beta
#  Sigma block works like Omega block
#  Bug fix to recognize ETA at end of NM-TRAN line
# 13 Feb 2013 version 1.008 beta
#  Model object VERBATIM sub-block identifies verbatim NM-TRAN code
#  Bug fix to finish omega block when followed by sigma block
# 18 Feb 2013 version 1.009 beta
#  Bug fix for covariance output in task object
#  VERBATIM sub-block for ABBREVIATED, ICALL.EQ.3
# 20 Feb 2013 version 1.010 beta
#  Bug fix for II, SS, ADDL, CMT data items
# 27 Feb 2013 version 1.011 beta
#  Revised VERBATIM block structure
#  Bug fix for trailing ! in variable names
#  Removed empty list for library param attribute
# 3 Mar 2013 version 1.012 beta
#  Revised one line VERBATIM block creation
#  Bug fix for tol_num
#  Bug fix for block numbers, block fix and SAME block
# 4 Mar 2013 version 1.013 beta
#  TARGET and VERBATIM blocks implemented
# 10 Mar 2013 version 1.014 beta
#  TARGET block re-implemented (location and first attributes)
#  MIXTURE and PRIOR blocks re-implemented
#  Logical operators '.AND.' and '.OR.' are now translated as ' && ' and ' || ' instead of ' & ' and ' | '
#  Bug fix for erroneous name detected in OMEGA SAME
#  Bug fix for parameter names using ADVAN=1 TRAN=1
#  Invalid char (.) in parameter names replaced with underscore
# 19 Mar 2013 version 1.015 beta
#  Bug in TARGET location
# 30 Mar 2013 version 1.016 beta
#  Bug fix for RANDOM_VARIABLE_DEFINITION location with conditional expression for ETA
#  PRIOR block moved from model object to parameter object
#  Task properties PARAMETER and MODEL blocks added
#  IGNORE and ACCEPT logical operators changed to || and &&
# 18 Apr 2013 version 1.017 beta
#  Math function names -> lowercase
#  MyEst function pointed to from task object
#  ESTIMATE block removed from MyEst function
# 20 Apr 2013 version 1.018 beta
#  MySIM function added to Task Properties
#  $WARN bug fix
# 24 Apr 2013 version 1.019 beta
#  Task object ACCEPT and IGNORE use =if(...) instead of =(...) to match MCL Specification
# 29 Apr 2013 version 1.020 beta
#  Changed PARAMETER_VARIABILIY block/same/diag structure to include attribute list
# 05 May 2013 version 1.021 beta
#  NM-TRAN estimation uses TARGET_CODE
#  Observation conditional and likelihood bug fix
#  Task properties EXECUTE block
# 06 May 2013 version 1.022 beta
#  ODE variables cleaned up
#  Task MODEL block bug fix
#  task sig bug fix
# 13 May 2013 version 1.023 beta
#  EXECUTE block temporarily removed
# 15 May 2013 version 1.024 beta
#  References to A() in MODEL PREDICTION block changed to amount.Acmtnum or amount.cmtname
#  ETA replacement bug fix when $OMEGA follows $SIGMA
# 17 May 2013 version 1.025 beta
#  AMTn supplied if $MODEL does not define compartment names
#  Conditional prediction block fix
#  DROP instruction temporarily removed
# 23 May 2013 version 1.027 beta
#  DROP and EXECUTE blocks restored
#  MIXTURE block bug when NSPOP followed P() fixed
#  MISSING no longer assigned a value
# 05 June 2013 version 1.028 beta
#  VARIABILITY 'block' changed to 'matrix'
#  TARGET_CODE block syntax changed.
#  IMPORT block added for nmadvan
# 15 June 2013 version 1.029 beta
#  Library function return value references with array index e.g. amount.param.A[1]
#  Fix bug in task properties estimate cov=y -> cov="y"
# 23 August 2013 version 1.030 beta
#  Fix bug with leading blank before TARGET_CODE block code
#  Library function return value references without param e.g. amount.A[1] (in function amt_reference)
#  Units for TIME and AMT added to HEADER and INPUT_VARIABLES
# 27 August 2013 version 1.031 beta
#  Bug fix so that Library function return value references amount.A[1] (in function amt_reference)
# 28 August 2013 version 1.032 beta
#  Define attribute is an option for categorical lists
#  SEX=list(type=categorical,define=list(female=0,male=1,MISSING=99),recode=list("F","M","UNK"))
# 30 August 2013 version 1.033 beta
#  Bug fix for categorical attributes
#  Units added to warf_PK_CONC example structural parameters and DV
#  Y prediction added for SIMULATION if there is no $ESTIMATION TARGET_CODE block
# 02 September 2013 version 1.034 beta
#  ncmt only specified for nmadvan model=5 or model=7
#  library expression re-written for BO1_EMAX example
# 14 September 2013 version 1.035 beta
#  CONTINUOUS and LIKELIHOOD attributes uppercase
#  DROP feature include in task properties DATA block
#  amount.A[*] used for nmadvan model=5 or model=7 instead of $MODEL name
# 19 September 2013 version 1.036 beta
#  IMPORT block added back for nmadvan
# 19 November 2013 version 1.037 beta
#  TARGET_CODE position option if verbatim contains "FIRST or "LAST
# 23 November 2013 version 1.038 beta
#  If $EST contains LIKE or -2LL then remove this option from $EST and generate LIKE in mdl
#  OMEGA BLOCK blanks replaced by comma bug fixed
#  LIKE attribute ruv changed from text to variable expression
# 25 November 2013 version 1.039 beta
#  If $EST contains LIKE or -2LL then generate LIKE in mdl (option not removed from $EST)
#  CALL RANDOM(n,R) creates R=runif(n)
# 29 November 2013 version 1.040 beta
#  Detect prediction with IF (logical) Y=prediction
#  Remove nul return from errorexit()
#  Use M2LL rather than -2LL to describe LIKE type
# 19 January 2014 version 1.041 beta
#  MDLPRED pragma to identify MODEL_PREDICTION block
# 10 May 2014 version 1.042 beta
#  Changes to data and model object block names for variables and SOURCE block attributes
#  sd and var replac "STDEV" and "variance" for consistency with R language
#  param list for nmadvan() changed to "A,F" 
#  IMPORT block uses '0' instead of current model, trans, ncmt
#  LIKE attribute for value changed from likelihood to prediction
#  units pragma for data items and parameters
# 11 May 2014 version 1.043 beta
#  library param attribute changed to output
# 20 May 2014 version 1.044 beta
# INDBLOCK pragma added
# leveltype for eta determined by existence of "ID" data item
# Single subject SIMULATION bug fixed
# 20 May 2014 version 1.045 beta
# Fix advan and tran recognition when comma used as delimiter
# 03 June 2014 version 1.046 beta
# Fix recognition of OMEGA SAME variables with a comment suffix
# 22 June 2014 version 1.047 beta
# IMPORT_BLOCK removed
# LIKE changed to Y for likelihood statements.
# A_0() without $DES added to MODEL_PREDICTION before LIBRARY
# 25 June 2014 version 1.048 beta
# TARGET_CODE block before attribute removed 
# 30 June 2014 version 1.049 beta
# Translation of multiple COMP on same line
# 08 July 2014 version 1.050 beta
# A_0() without $DES changed from MODEL_PREDICTION to INDIVIDUAL_VARIABLES
# NOAPP recognized as $TABLE option
# Multiplicative RUV expressions eg. C*EXP(eps) converted to additive
# ESTBLOCK implemented
# 13 July 2014 version 1.051 beta
# Help statements use symbolic names for block
# Option to print $TABLE as TARGET_CODE
# 15 September 2014 version 1.052 beta
# $THETAI and $THETAR support
# 22 September 2014 version 1.053 beta
# GAMLN(x) translated to lfactorial(x-1) so GAMLN(DV+1) becomes lfactorial(DV+1-1) i.e. lfactorial(DV)
# Categorical defined code tidied up
# 30 September 2014 version 2.000 beta
#   MDL updated for consistency with MCL Spec Draft 6 (MCL6) and MDL Library Draft 2.
# 21 October 2014 version 2.001 beta
#   MDL5 to MDLPRED6 conversion   
# 22 October 2014 version 2.002 beta
#   Fix units bug for model object parameter lists
# 23 October 2014 version 2.003 beta
#   Use Monolix variance=no if NONMEM OMEGA is SD
# 04 March 2015 version 2.004 beta
#   MDL6 for IOG Product4
# 05 March 2015 version 2.005 beta
#   piecewise function changed for multiple when
# 06 March 2015 version 2.006 beta
#   Bug fix for covariance/correlation in parameter object
# 15 March 2015 version 2.007 beta
#   Removed "+1-1" from lfactorial()
#   Prototype MACRO sub-block
# 16 March 2015 version 2.008 beta
#   PKMACRO implementation
# 31 March 2015 version 2.009 beta
#   PKMACRO attributes all lower case
#   Identical assignments of variables ignored
#   Attributes for defining categorical variables changed to matrix format
#   Matrix name not included in model object
#   Model and data objects mangled into the Consortium Product 4 format
# 01 April 2015 version 2.010 beta
#   PKMACRO distribution rate constants bug fixed.
#   COMPARTMENT sub-block replaces PKMACRO sub-block
# 03 April 2015 version 2.011 beta
#   Use of type=compartment no longer required for COMPARTMENT sub-block
#   use=cmt variable define attribute created using COMPARTMENT sub-block names
# 10 April 2015 version 2.012 beta
#   Removed feature to remove identical assignments (breaks recursive random variable assignments)
#   COMPARTMENT sub-block attribute cmt changed to modelCmt
#   Separate type=compartment added to COMPARTMENT sub-block
#   Random variable distribution changed from list to ~Normal()
# 11 April 2015 version 2.013 beta
#   Bug fix for VARIABILITY_LEVELS type attribute
#   DECLARED_VARIABLES no longer uses comma separators
#   mogobj OBJECTS and MAPPING blocks added
#   OBSERVATION block output does not use piecewise or conditional statements
#   Removed nmtran output of COMPARTMENTS because it showed nothing useful
# 12 April 2015 version 2.014 beta
#   Bizarre changes for Product 4
#   IDV block added to model object
#   MODEL_OUTPUT_VARIABLES block removed
# 18 April 2015 version 2.015 beta
#   MODEL_OUTPUT_VARIABLES block restored
#   predId -> predID; /70kg -? /kg*70
#   data_declare no longer includes model object category names
#   if(bool) Y expr -> if (bool) then Y expr endif
#   Product 4 matrix offdiagonal
# 21 April 2015 version 2.016 beta
#   Default options changed to MDLHELP=N and NMTRAN=N
#   Bug fix for var and sd attributes in parameter object and RANDOM_VARIABLES_DEFINITION
#   Bug fix for use=amt (empty link removed and type changed to continuous)
#   @MCL macro
#   PRED models include all data items as covariates except TIME
#   predID -> predid
# 22 April 2015 version 2.017 beta
#   duplicate COMPARTMENT name bug fixed
#   BOV parameters and code suppressed
# 26 April 2015 version 2.018 beta
#   =runif(seed) changed to ~Uniform(0,1,seed)
#   Y1_obs -> Y if only one DV
#   IDV selected from TIME then CONC then CP
#   rate -> inputRate, tk0->inputDur
# 04 May 2015 version 2.019 beta
#   IDV declared as T if there is a DEQ block
#   Simple ADVAN[5|7] translation to COMPARTMENT sub-block
# 06 May 2015 version 2.020 beta
#   Bug fix for ELSEBLOCK initialization
# 16 May 2015 version 2.021 beta
#   inputRate -> modelRate, inputDur -> modelDur
#   predid -> predId
# 18 May 2015 version 2.022 beta
#   BLOCK SAME only used to remove variables when OCC data item exists (hasocc==1)
#   Bug fix when TIME used as covariate with DEQ and ivd=T
# 20 May 2015 version 2.023 beta
#   Task properties object MODEL block removed from ispidgin conversions
# 21 May 2015 version 2.024 beta
# 16 June 2015 version 2.025 beta
#   Transfer compartment 'ktr' changed to 'kt'
# 23 June 2015 version 2.026 beta
#   COMPARTMENT list for DEQ input compartments with input options (R, D, ALAG, F)
#   A(*) translated to COMPARTMENT names for standard ADVANs
# 25 June 2015 version 2.027 beta
#   INDBLOCK pragma changed to MDLIND
#   Bug fix for COMPARTMENT list for DEQ input compartments
# 27 June 2015 version 2.028 beta
#   Bug fix for model input variables ending in "T"
#   Bug fix for RVD block when there is no use=id
# 28 June 2015 version 2.029 beta
#   Updated MCL pragmas (@MCL_",  @MCL_IGNORE, @MCL_COVARIATES)
# 29 June 2015 version 2.030 beta
# Missing value changed to 99 because of PharmML bug
