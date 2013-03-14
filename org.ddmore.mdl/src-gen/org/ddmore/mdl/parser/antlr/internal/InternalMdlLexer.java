package org.ddmore.mdl.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalMdlLexer extends Lexer {
    public static final int T__68=68;
    public static final int RULE_BOOLEAN=9;
    public static final int T__69=69;
    public static final int T__66=66;
    public static final int RULE_ID=4;
    public static final int T__67=67;
    public static final int T__29=29;
    public static final int T__64=64;
    public static final int T__28=28;
    public static final int T__65=65;
    public static final int T__27=27;
    public static final int T__62=62;
    public static final int T__26=26;
    public static final int T__63=63;
    public static final int RULE_OCTAL_ESCAPE=14;
    public static final int T__25=25;
    public static final int T__24=24;
    public static final int RULE_ESCAPE_SEQUENCE=12;
    public static final int RULE_BEGIN=5;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int RULE_ANY_OTHER=21;
    public static final int RULE_HEXDIGIT=15;
    public static final int T__61=61;
    public static final int EOF=-1;
    public static final int T__60=60;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__59=59;
    public static final int RULE_INT=18;
    public static final int RULE_UNICODE_ESCAPE=13;
    public static final int T__50=50;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__46=46;
    public static final int T__80=80;
    public static final int T__47=47;
    public static final int T__81=81;
    public static final int T__44=44;
    public static final int T__82=82;
    public static final int T__45=45;
    public static final int T__83=83;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__85=85;
    public static final int RULE_FLOAT=11;
    public static final int T__84=84;
    public static final int RULE_SL_COMMENT=17;
    public static final int T__87=87;
    public static final int T__86=86;
    public static final int RULE_EXTERNAL_CODE=7;
    public static final int T__89=89;
    public static final int T__88=88;
    public static final int RULE_ML_COMMENT=19;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_STRING=8;
    public static final int T__33=33;
    public static final int T__71=71;
    public static final int T__34=34;
    public static final int T__72=72;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__70=70;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int RULE_END=6;
    public static final int T__39=39;
    public static final int RULE_EXPONENT=16;
    public static final int T__76=76;
    public static final int RULE_WS=20;
    public static final int T__75=75;
    public static final int T__74=74;
    public static final int T__73=73;
    public static final int T__79=79;
    public static final int RULE_INTEGER=10;
    public static final int T__78=78;
    public static final int T__77=77;

    // delegates
    // delegators

    public InternalMdlLexer() {;} 
    public InternalMdlLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalMdlLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g"; }

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:11:7: ( '=' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:11:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:12:7: ( 'mdlobj' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:12:9: 'mdlobj'
            {
            match("mdlobj"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:13:7: ( 'parobj' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:13:9: 'parobj'
            {
            match("parobj"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:14:7: ( 'dataobj' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:14:9: 'dataobj'
            {
            match("dataobj"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:15:7: ( 'taskobj' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:15:9: 'taskobj'
            {
            match("taskobj"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:16:7: ( 'telobj' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:16:9: 'telobj'
            {
            match("telobj"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:17:7: ( 'INDIVIDUAL_VARIABLES' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:17:9: 'INDIVIDUAL_VARIABLES'
            {
            match("INDIVIDUAL_VARIABLES"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:18:7: ( 'MODEL_PREDICTION' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:18:9: 'MODEL_PREDICTION'
            {
            match("MODEL_PREDICTION"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:19:7: ( 'RANDOM_VARIABLE_DEFINITION' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:19:9: 'RANDOM_VARIABLE_DEFINITION'
            {
            match("RANDOM_VARIABLE_DEFINITION"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:20:7: ( 'INPUT_VARIABLES' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:20:9: 'INPUT_VARIABLES'
            {
            match("INPUT_VARIABLES"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:21:7: ( 'STRUCTURAL_PARAMETERS' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:21:9: 'STRUCTURAL_PARAMETERS'
            {
            match("STRUCTURAL_PARAMETERS"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "T__33"
    public final void mT__33() throws RecognitionException {
        try {
            int _type = T__33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:22:7: ( 'VARIABILITY_PARAMETERS' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:22:9: 'VARIABILITY_PARAMETERS'
            {
            match("VARIABILITY_PARAMETERS"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__33"

    // $ANTLR start "T__34"
    public final void mT__34() throws RecognitionException {
        try {
            int _type = T__34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:23:7: ( 'OUTPUT_VARIABLES' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:23:9: 'OUTPUT_VARIABLES'
            {
            match("OUTPUT_VARIABLES"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__34"

    // $ANTLR start "T__35"
    public final void mT__35() throws RecognitionException {
        try {
            int _type = T__35;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:24:7: ( 'GROUP_VARIABLES' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:24:9: 'GROUP_VARIABLES'
            {
            match("GROUP_VARIABLES"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__35"

    // $ANTLR start "T__36"
    public final void mT__36() throws RecognitionException {
        try {
            int _type = T__36;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:25:7: ( 'OBSERVATION' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:25:9: 'OBSERVATION'
            {
            match("OBSERVATION"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__36"

    // $ANTLR start "T__37"
    public final void mT__37() throws RecognitionException {
        try {
            int _type = T__37;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:26:7: ( 'ESTIMATION' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:26:9: 'ESTIMATION'
            {
            match("ESTIMATION"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__37"

    // $ANTLR start "T__38"
    public final void mT__38() throws RecognitionException {
        try {
            int _type = T__38;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:27:7: ( 'SIMULATION' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:27:9: 'SIMULATION'
            {
            match("SIMULATION"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__38"

    // $ANTLR start "T__39"
    public final void mT__39() throws RecognitionException {
        try {
            int _type = T__39;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:28:7: ( 'STRUCTURAL' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:28:9: 'STRUCTURAL'
            {
            match("STRUCTURAL"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__39"

    // $ANTLR start "T__40"
    public final void mT__40() throws RecognitionException {
        try {
            int _type = T__40;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:29:7: ( 'VARIABILITY' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:29:9: 'VARIABILITY'
            {
            match("VARIABILITY"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__40"

    // $ANTLR start "T__41"
    public final void mT__41() throws RecognitionException {
        try {
            int _type = T__41;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:30:7: ( 'HEADER' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:30:9: 'HEADER'
            {
            match("HEADER"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__41"

    // $ANTLR start "T__42"
    public final void mT__42() throws RecognitionException {
        try {
            int _type = T__42;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:31:7: ( 'FILE' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:31:9: 'FILE'
            {
            match("FILE"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__42"

    // $ANTLR start "T__43"
    public final void mT__43() throws RecognitionException {
        try {
            int _type = T__43;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:32:7: ( 'PARAMETERS' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:32:9: 'PARAMETERS'
            {
            match("PARAMETERS"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__43"

    // $ANTLR start "T__44"
    public final void mT__44() throws RecognitionException {
        try {
            int _type = T__44;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:33:7: ( 'DATA' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:33:9: 'DATA'
            {
            match("DATA"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__44"

    // $ANTLR start "T__45"
    public final void mT__45() throws RecognitionException {
        try {
            int _type = T__45;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:34:7: ( 'LIBRARY' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:34:9: 'LIBRARY'
            {
            match("LIBRARY"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__45"

    // $ANTLR start "T__46"
    public final void mT__46() throws RecognitionException {
        try {
            int _type = T__46;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:35:7: ( 'ODE' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:35:9: 'ODE'
            {
            match("ODE"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__46"

    // $ANTLR start "T__47"
    public final void mT__47() throws RecognitionException {
        try {
            int _type = T__47;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:36:7: ( 'block' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:36:9: 'block'
            {
            match("block"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__47"

    // $ANTLR start "T__48"
    public final void mT__48() throws RecognitionException {
        try {
            int _type = T__48;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:37:7: ( 'diag' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:37:9: 'diag'
            {
            match("diag"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__48"

    // $ANTLR start "T__49"
    public final void mT__49() throws RecognitionException {
        try {
            int _type = T__49;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:38:7: ( '(' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:38:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__49"

    // $ANTLR start "T__50"
    public final void mT__50() throws RecognitionException {
        try {
            int _type = T__50;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:39:7: ( ')' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:39:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__50"

    // $ANTLR start "T__51"
    public final void mT__51() throws RecognitionException {
        try {
            int _type = T__51;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:40:7: ( 'INLINE' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:40:9: 'INLINE'
            {
            match("INLINE"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__51"

    // $ANTLR start "T__52"
    public final void mT__52() throws RecognitionException {
        try {
            int _type = T__52;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:41:7: ( 'DESIGN' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:41:9: 'DESIGN'
            {
            match("DESIGN"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__52"

    // $ANTLR start "T__53"
    public final void mT__53() throws RecognitionException {
        try {
            int _type = T__53;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:42:7: ( 'RSSCRIPT' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:42:9: 'RSSCRIPT'
            {
            match("RSSCRIPT"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__53"

    // $ANTLR start "T__54"
    public final void mT__54() throws RecognitionException {
        try {
            int _type = T__54;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:43:7: ( '.' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:43:9: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__54"

    // $ANTLR start "T__55"
    public final void mT__55() throws RecognitionException {
        try {
            int _type = T__55;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:44:7: ( 'function' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:44:9: 'function'
            {
            match("function"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__55"

    // $ANTLR start "T__56"
    public final void mT__56() throws RecognitionException {
        try {
            int _type = T__56;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:45:7: ( 'ESTIMATE' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:45:9: 'ESTIMATE'
            {
            match("ESTIMATE"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__56"

    // $ANTLR start "T__57"
    public final void mT__57() throws RecognitionException {
        try {
            int _type = T__57;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:46:7: ( 'SIMULATE' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:46:9: 'SIMULATE'
            {
            match("SIMULATE"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__57"

    // $ANTLR start "T__58"
    public final void mT__58() throws RecognitionException {
        try {
            int _type = T__58;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:47:7: ( ',' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:47:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__58"

    // $ANTLR start "T__59"
    public final void mT__59() throws RecognitionException {
        try {
            int _type = T__59;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:48:7: ( 'VERBATIM' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:48:9: 'VERBATIM'
            {
            match("VERBATIM"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__59"

    // $ANTLR start "T__60"
    public final void mT__60() throws RecognitionException {
        try {
            int _type = T__60;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:49:7: ( 'NMTRAN' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:49:9: 'NMTRAN'
            {
            match("NMTRAN"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__60"

    // $ANTLR start "T__61"
    public final void mT__61() throws RecognitionException {
        try {
            int _type = T__61;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:50:7: ( 'MLXTRAN' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:50:9: 'MLXTRAN'
            {
            match("MLXTRAN"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__61"

    // $ANTLR start "T__62"
    public final void mT__62() throws RecognitionException {
        try {
            int _type = T__62;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:51:7: ( 'PML' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:51:9: 'PML'
            {
            match("PML"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__62"

    // $ANTLR start "T__63"
    public final void mT__63() throws RecognitionException {
        try {
            int _type = T__63;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:52:7: ( 'BUGS' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:52:9: 'BUGS'
            {
            match("BUGS"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__63"

    // $ANTLR start "T__64"
    public final void mT__64() throws RecognitionException {
        try {
            int _type = T__64;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:53:7: ( 'RCODE' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:53:9: 'RCODE'
            {
            match("RCODE"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__64"

    // $ANTLR start "T__65"
    public final void mT__65() throws RecognitionException {
        try {
            int _type = T__65;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:54:7: ( 'MATLAB' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:54:9: 'MATLAB'
            {
            match("MATLAB"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__65"

    // $ANTLR start "T__66"
    public final void mT__66() throws RecognitionException {
        try {
            int _type = T__66;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:55:7: ( '+' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:55:9: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__66"

    // $ANTLR start "T__67"
    public final void mT__67() throws RecognitionException {
        try {
            int _type = T__67;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:56:7: ( 'list' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:56:9: 'list'
            {
            match("list"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__67"

    // $ANTLR start "T__68"
    public final void mT__68() throws RecognitionException {
        try {
            int _type = T__68;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:57:7: ( 'ode' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:57:9: 'ode'
            {
            match("ode"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__68"

    // $ANTLR start "T__69"
    public final void mT__69() throws RecognitionException {
        try {
            int _type = T__69;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:58:7: ( '~' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:58:9: '~'
            {
            match('~'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__69"

    // $ANTLR start "T__70"
    public final void mT__70() throws RecognitionException {
        try {
            int _type = T__70;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:59:7: ( 'if' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:59:9: 'if'
            {
            match("if"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__70"

    // $ANTLR start "T__71"
    public final void mT__71() throws RecognitionException {
        try {
            int _type = T__71;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:60:7: ( 'else' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:60:9: 'else'
            {
            match("else"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__71"

    // $ANTLR start "T__72"
    public final void mT__72() throws RecognitionException {
        try {
            int _type = T__72;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:61:7: ( '?' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:61:9: '?'
            {
            match('?'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__72"

    // $ANTLR start "T__73"
    public final void mT__73() throws RecognitionException {
        try {
            int _type = T__73;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:62:7: ( ':' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:62:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__73"

    // $ANTLR start "T__74"
    public final void mT__74() throws RecognitionException {
        try {
            int _type = T__74;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:63:7: ( '!' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:63:9: '!'
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__74"

    // $ANTLR start "T__75"
    public final void mT__75() throws RecognitionException {
        try {
            int _type = T__75;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:64:7: ( '[' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:64:9: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__75"

    // $ANTLR start "T__76"
    public final void mT__76() throws RecognitionException {
        try {
            int _type = T__76;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:65:7: ( ']' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:65:9: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__76"

    // $ANTLR start "T__77"
    public final void mT__77() throws RecognitionException {
        try {
            int _type = T__77;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:66:7: ( '&&' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:66:9: '&&'
            {
            match("&&"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__77"

    // $ANTLR start "T__78"
    public final void mT__78() throws RecognitionException {
        try {
            int _type = T__78;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:67:7: ( '||' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:67:9: '||'
            {
            match("||"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__78"

    // $ANTLR start "T__79"
    public final void mT__79() throws RecognitionException {
        try {
            int _type = T__79;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:68:7: ( '<' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:68:9: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__79"

    // $ANTLR start "T__80"
    public final void mT__80() throws RecognitionException {
        try {
            int _type = T__80;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:69:7: ( '>' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:69:9: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__80"

    // $ANTLR start "T__81"
    public final void mT__81() throws RecognitionException {
        try {
            int _type = T__81;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:70:7: ( '<=' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:70:9: '<='
            {
            match("<="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__81"

    // $ANTLR start "T__82"
    public final void mT__82() throws RecognitionException {
        try {
            int _type = T__82;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:71:7: ( '>=' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:71:9: '>='
            {
            match(">="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__82"

    // $ANTLR start "T__83"
    public final void mT__83() throws RecognitionException {
        try {
            int _type = T__83;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:72:7: ( '==' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:72:9: '=='
            {
            match("=="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__83"

    // $ANTLR start "T__84"
    public final void mT__84() throws RecognitionException {
        try {
            int _type = T__84;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:73:7: ( '!=' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:73:9: '!='
            {
            match("!="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__84"

    // $ANTLR start "T__85"
    public final void mT__85() throws RecognitionException {
        try {
            int _type = T__85;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:74:7: ( '^' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:74:9: '^'
            {
            match('^'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__85"

    // $ANTLR start "T__86"
    public final void mT__86() throws RecognitionException {
        try {
            int _type = T__86;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:75:7: ( '*' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:75:9: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__86"

    // $ANTLR start "T__87"
    public final void mT__87() throws RecognitionException {
        try {
            int _type = T__87;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:76:7: ( '/' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:76:9: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__87"

    // $ANTLR start "T__88"
    public final void mT__88() throws RecognitionException {
        try {
            int _type = T__88;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:77:7: ( '%' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:77:9: '%'
            {
            match('%'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__88"

    // $ANTLR start "T__89"
    public final void mT__89() throws RecognitionException {
        try {
            int _type = T__89;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:78:7: ( '-' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:78:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__89"

    // $ANTLR start "RULE_EXTERNAL_CODE"
    public final void mRULE_EXTERNAL_CODE() throws RecognitionException {
        try {
            int _type = RULE_EXTERNAL_CODE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5466:20: ( '***' ( options {greedy=false; } : . )* '***' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5466:22: '***' ( options {greedy=false; } : . )* '***'
            {
            match("***"); 

            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5466:28: ( options {greedy=false; } : . )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0=='*') ) {
                    int LA1_1 = input.LA(2);

                    if ( (LA1_1=='*') ) {
                        int LA1_3 = input.LA(3);

                        if ( (LA1_3=='*') ) {
                            alt1=2;
                        }
                        else if ( ((LA1_3>='\u0000' && LA1_3<=')')||(LA1_3>='+' && LA1_3<='\uFFFF')) ) {
                            alt1=1;
                        }


                    }
                    else if ( ((LA1_1>='\u0000' && LA1_1<=')')||(LA1_1>='+' && LA1_1<='\uFFFF')) ) {
                        alt1=1;
                    }


                }
                else if ( ((LA1_0>='\u0000' && LA1_0<=')')||(LA1_0>='+' && LA1_0<='\uFFFF')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5466:56: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            match("***"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_EXTERNAL_CODE"

    // $ANTLR start "RULE_STRING"
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5468:13: ( '\"' ( RULE_ESCAPE_SEQUENCE | ~ ( ( '\\\\' | '\"' ) ) )* '\"' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5468:15: '\"' ( RULE_ESCAPE_SEQUENCE | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
            {
            match('\"'); 
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5468:19: ( RULE_ESCAPE_SEQUENCE | ~ ( ( '\\\\' | '\"' ) ) )*
            loop2:
            do {
                int alt2=3;
                int LA2_0 = input.LA(1);

                if ( (LA2_0=='\\') ) {
                    alt2=1;
                }
                else if ( ((LA2_0>='\u0000' && LA2_0<='!')||(LA2_0>='#' && LA2_0<='[')||(LA2_0>=']' && LA2_0<='\uFFFF')) ) {
                    alt2=2;
                }


                switch (alt2) {
            	case 1 :
            	    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5468:20: RULE_ESCAPE_SEQUENCE
            	    {
            	    mRULE_ESCAPE_SEQUENCE(); 

            	    }
            	    break;
            	case 2 :
            	    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5468:41: ~ ( ( '\\\\' | '\"' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_STRING"

    // $ANTLR start "RULE_ESCAPE_SEQUENCE"
    public final void mRULE_ESCAPE_SEQUENCE() throws RecognitionException {
        try {
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5470:31: ( ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | RULE_UNICODE_ESCAPE | RULE_OCTAL_ESCAPE ) )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5470:33: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | RULE_UNICODE_ESCAPE | RULE_OCTAL_ESCAPE )
            {
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5470:33: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | RULE_UNICODE_ESCAPE | RULE_OCTAL_ESCAPE )
            int alt3=3;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='\\') ) {
                switch ( input.LA(2) ) {
                case '\"':
                case '\'':
                case '\\':
                case 'b':
                case 'f':
                case 'n':
                case 'r':
                case 't':
                    {
                    alt3=1;
                    }
                    break;
                case 'u':
                    {
                    alt3=2;
                    }
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                    {
                    alt3=3;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5470:34: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' )
                    {
                    match('\\'); 
                    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;
                case 2 :
                    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5470:75: RULE_UNICODE_ESCAPE
                    {
                    mRULE_UNICODE_ESCAPE(); 

                    }
                    break;
                case 3 :
                    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5470:95: RULE_OCTAL_ESCAPE
                    {
                    mRULE_OCTAL_ESCAPE(); 

                    }
                    break;

            }


            }

        }
        finally {
        }
    }
    // $ANTLR end "RULE_ESCAPE_SEQUENCE"

    // $ANTLR start "RULE_OCTAL_ESCAPE"
    public final void mRULE_OCTAL_ESCAPE() throws RecognitionException {
        try {
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5472:28: ( ( '\\\\' '0' .. '3' '0' .. '7' '0' .. '7' | '\\\\' '0' .. '7' '0' .. '7' | '\\\\' '0' .. '7' ) )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5472:30: ( '\\\\' '0' .. '3' '0' .. '7' '0' .. '7' | '\\\\' '0' .. '7' '0' .. '7' | '\\\\' '0' .. '7' )
            {
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5472:30: ( '\\\\' '0' .. '3' '0' .. '7' '0' .. '7' | '\\\\' '0' .. '7' '0' .. '7' | '\\\\' '0' .. '7' )
            int alt4=3;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='\\') ) {
                int LA4_1 = input.LA(2);

                if ( ((LA4_1>='0' && LA4_1<='3')) ) {
                    int LA4_2 = input.LA(3);

                    if ( ((LA4_2>='0' && LA4_2<='7')) ) {
                        int LA4_4 = input.LA(4);

                        if ( ((LA4_4>='0' && LA4_4<='7')) ) {
                            alt4=1;
                        }
                        else {
                            alt4=2;}
                    }
                    else {
                        alt4=3;}
                }
                else if ( ((LA4_1>='4' && LA4_1<='7')) ) {
                    int LA4_3 = input.LA(3);

                    if ( ((LA4_3>='0' && LA4_3<='7')) ) {
                        alt4=2;
                    }
                    else {
                        alt4=3;}
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5472:31: '\\\\' '0' .. '3' '0' .. '7' '0' .. '7'
                    {
                    match('\\'); 
                    matchRange('0','3'); 
                    matchRange('0','7'); 
                    matchRange('0','7'); 

                    }
                    break;
                case 2 :
                    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5472:63: '\\\\' '0' .. '7' '0' .. '7'
                    {
                    match('\\'); 
                    matchRange('0','7'); 
                    matchRange('0','7'); 

                    }
                    break;
                case 3 :
                    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5472:86: '\\\\' '0' .. '7'
                    {
                    match('\\'); 
                    matchRange('0','7'); 

                    }
                    break;

            }


            }

        }
        finally {
        }
    }
    // $ANTLR end "RULE_OCTAL_ESCAPE"

    // $ANTLR start "RULE_UNICODE_ESCAPE"
    public final void mRULE_UNICODE_ESCAPE() throws RecognitionException {
        try {
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5474:30: ( '\\\\' 'u' RULE_HEXDIGIT RULE_HEXDIGIT RULE_HEXDIGIT RULE_HEXDIGIT )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5474:32: '\\\\' 'u' RULE_HEXDIGIT RULE_HEXDIGIT RULE_HEXDIGIT RULE_HEXDIGIT
            {
            match('\\'); 
            match('u'); 
            mRULE_HEXDIGIT(); 
            mRULE_HEXDIGIT(); 
            mRULE_HEXDIGIT(); 
            mRULE_HEXDIGIT(); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "RULE_UNICODE_ESCAPE"

    // $ANTLR start "RULE_HEXDIGIT"
    public final void mRULE_HEXDIGIT() throws RecognitionException {
        try {
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5476:24: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5476:26: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
            {
            if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "RULE_HEXDIGIT"

    // $ANTLR start "RULE_BOOLEAN"
    public final void mRULE_BOOLEAN() throws RecognitionException {
        try {
            int _type = RULE_BOOLEAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5478:14: ( ( 'true' | 'false' ) )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5478:16: ( 'true' | 'false' )
            {
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5478:16: ( 'true' | 'false' )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='t') ) {
                alt5=1;
            }
            else if ( (LA5_0=='f') ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5478:17: 'true'
                    {
                    match("true"); 


                    }
                    break;
                case 2 :
                    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5478:24: 'false'
                    {
                    match("false"); 


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_BOOLEAN"

    // $ANTLR start "RULE_INTEGER"
    public final void mRULE_INTEGER() throws RecognitionException {
        try {
            int _type = RULE_INTEGER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5480:14: ( ( '0' | '1' .. '9' ( '0' .. '9' )* ) )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5480:16: ( '0' | '1' .. '9' ( '0' .. '9' )* )
            {
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5480:16: ( '0' | '1' .. '9' ( '0' .. '9' )* )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0=='0') ) {
                alt7=1;
            }
            else if ( ((LA7_0>='1' && LA7_0<='9')) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5480:17: '0'
                    {
                    match('0'); 

                    }
                    break;
                case 2 :
                    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5480:21: '1' .. '9' ( '0' .. '9' )*
                    {
                    matchRange('1','9'); 
                    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5480:30: ( '0' .. '9' )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( ((LA6_0>='0' && LA6_0<='9')) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5480:31: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_INTEGER"

    // $ANTLR start "RULE_FLOAT"
    public final void mRULE_FLOAT() throws RecognitionException {
        try {
            int _type = RULE_FLOAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5482:12: ( ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( RULE_EXPONENT )? | '.' ( '0' .. '9' )+ ( RULE_EXPONENT )? | ( '0' .. '9' )+ RULE_EXPONENT | '0' .. '9' ) )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5482:14: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( RULE_EXPONENT )? | '.' ( '0' .. '9' )+ ( RULE_EXPONENT )? | ( '0' .. '9' )+ RULE_EXPONENT | '0' .. '9' )
            {
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5482:14: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( RULE_EXPONENT )? | '.' ( '0' .. '9' )+ ( RULE_EXPONENT )? | ( '0' .. '9' )+ RULE_EXPONENT | '0' .. '9' )
            int alt14=4;
            alt14 = dfa14.predict(input);
            switch (alt14) {
                case 1 :
                    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5482:15: ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( RULE_EXPONENT )?
                    {
                    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5482:15: ( '0' .. '9' )+
                    int cnt8=0;
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( ((LA8_0>='0' && LA8_0<='9')) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5482:16: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt8 >= 1 ) break loop8;
                                EarlyExitException eee =
                                    new EarlyExitException(8, input);
                                throw eee;
                        }
                        cnt8++;
                    } while (true);

                    match('.'); 
                    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5482:31: ( '0' .. '9' )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( ((LA9_0>='0' && LA9_0<='9')) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5482:32: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);

                    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5482:43: ( RULE_EXPONENT )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0=='E'||LA10_0=='e') ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5482:43: RULE_EXPONENT
                            {
                            mRULE_EXPONENT(); 

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5482:58: '.' ( '0' .. '9' )+ ( RULE_EXPONENT )?
                    {
                    match('.'); 
                    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5482:62: ( '0' .. '9' )+
                    int cnt11=0;
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( ((LA11_0>='0' && LA11_0<='9')) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5482:63: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt11 >= 1 ) break loop11;
                                EarlyExitException eee =
                                    new EarlyExitException(11, input);
                                throw eee;
                        }
                        cnt11++;
                    } while (true);

                    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5482:74: ( RULE_EXPONENT )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0=='E'||LA12_0=='e') ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5482:74: RULE_EXPONENT
                            {
                            mRULE_EXPONENT(); 

                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5482:89: ( '0' .. '9' )+ RULE_EXPONENT
                    {
                    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5482:89: ( '0' .. '9' )+
                    int cnt13=0;
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( ((LA13_0>='0' && LA13_0<='9')) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5482:90: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt13 >= 1 ) break loop13;
                                EarlyExitException eee =
                                    new EarlyExitException(13, input);
                                throw eee;
                        }
                        cnt13++;
                    } while (true);

                    mRULE_EXPONENT(); 

                    }
                    break;
                case 4 :
                    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5482:115: '0' .. '9'
                    {
                    matchRange('0','9'); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_FLOAT"

    // $ANTLR start "RULE_EXPONENT"
    public final void mRULE_EXPONENT() throws RecognitionException {
        try {
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5484:24: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5484:26: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5484:36: ( '+' | '-' )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0=='+'||LA15_0=='-') ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;

            }

            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5484:47: ( '0' .. '9' )+
            int cnt16=0;
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( ((LA16_0>='0' && LA16_0<='9')) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5484:48: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt16 >= 1 ) break loop16;
                        EarlyExitException eee =
                            new EarlyExitException(16, input);
                        throw eee;
                }
                cnt16++;
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end "RULE_EXPONENT"

    // $ANTLR start "RULE_SL_COMMENT"
    public final void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5486:17: ( '#' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5486:19: '#' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match('#'); 
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5486:23: (~ ( ( '\\n' | '\\r' ) ) )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( ((LA17_0>='\u0000' && LA17_0<='\t')||(LA17_0>='\u000B' && LA17_0<='\f')||(LA17_0>='\u000E' && LA17_0<='\uFFFF')) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5486:23: ~ ( ( '\\n' | '\\r' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);

            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5486:39: ( ( '\\r' )? '\\n' )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0=='\n'||LA19_0=='\r') ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5486:40: ( '\\r' )? '\\n'
                    {
                    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5486:40: ( '\\r' )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0=='\r') ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5486:40: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }

                    match('\n'); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_SL_COMMENT"

    // $ANTLR start "RULE_BEGIN"
    public final void mRULE_BEGIN() throws RecognitionException {
        try {
            int _type = RULE_BEGIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5488:12: ( '{' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5488:14: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_BEGIN"

    // $ANTLR start "RULE_END"
    public final void mRULE_END() throws RecognitionException {
        try {
            int _type = RULE_END;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5490:10: ( '}' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5490:12: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_END"

    // $ANTLR start "RULE_ID"
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5492:9: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5492:11: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5492:11: ( '^' )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0=='^') ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5492:11: '^'
                    {
                    match('^'); 

                    }
                    break;

            }

            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5492:40: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( ((LA21_0>='0' && LA21_0<='9')||(LA21_0>='A' && LA21_0<='Z')||LA21_0=='_'||(LA21_0>='a' && LA21_0<='z')) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ID"

    // $ANTLR start "RULE_INT"
    public final void mRULE_INT() throws RecognitionException {
        try {
            int _type = RULE_INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5494:10: ( ( '0' .. '9' )+ )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5494:12: ( '0' .. '9' )+
            {
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5494:12: ( '0' .. '9' )+
            int cnt22=0;
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( ((LA22_0>='0' && LA22_0<='9')) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5494:13: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt22 >= 1 ) break loop22;
                        EarlyExitException eee =
                            new EarlyExitException(22, input);
                        throw eee;
                }
                cnt22++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_INT"

    // $ANTLR start "RULE_ML_COMMENT"
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5496:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5496:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5496:24: ( options {greedy=false; } : . )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0=='*') ) {
                    int LA23_1 = input.LA(2);

                    if ( (LA23_1=='/') ) {
                        alt23=2;
                    }
                    else if ( ((LA23_1>='\u0000' && LA23_1<='.')||(LA23_1>='0' && LA23_1<='\uFFFF')) ) {
                        alt23=1;
                    }


                }
                else if ( ((LA23_0>='\u0000' && LA23_0<=')')||(LA23_0>='+' && LA23_0<='\uFFFF')) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5496:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);

            match("*/"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ML_COMMENT"

    // $ANTLR start "RULE_WS"
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5498:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5498:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5498:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt24=0;
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( ((LA24_0>='\t' && LA24_0<='\n')||LA24_0=='\r'||LA24_0==' ') ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt24 >= 1 ) break loop24;
                        EarlyExitException eee =
                            new EarlyExitException(24, input);
                        throw eee;
                }
                cnt24++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_WS"

    // $ANTLR start "RULE_ANY_OTHER"
    public final void mRULE_ANY_OTHER() throws RecognitionException {
        try {
            int _type = RULE_ANY_OTHER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5500:16: ( . )
            // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:5500:18: .
            {
            matchAny(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ANY_OTHER"

    public void mTokens() throws RecognitionException {
        // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:8: ( T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | RULE_EXTERNAL_CODE | RULE_STRING | RULE_BOOLEAN | RULE_INTEGER | RULE_FLOAT | RULE_SL_COMMENT | RULE_BEGIN | RULE_END | RULE_ID | RULE_INT | RULE_ML_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt25=81;
        alt25 = dfa25.predict(input);
        switch (alt25) {
            case 1 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:10: T__22
                {
                mT__22(); 

                }
                break;
            case 2 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:16: T__23
                {
                mT__23(); 

                }
                break;
            case 3 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:22: T__24
                {
                mT__24(); 

                }
                break;
            case 4 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:28: T__25
                {
                mT__25(); 

                }
                break;
            case 5 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:34: T__26
                {
                mT__26(); 

                }
                break;
            case 6 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:40: T__27
                {
                mT__27(); 

                }
                break;
            case 7 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:46: T__28
                {
                mT__28(); 

                }
                break;
            case 8 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:52: T__29
                {
                mT__29(); 

                }
                break;
            case 9 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:58: T__30
                {
                mT__30(); 

                }
                break;
            case 10 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:64: T__31
                {
                mT__31(); 

                }
                break;
            case 11 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:70: T__32
                {
                mT__32(); 

                }
                break;
            case 12 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:76: T__33
                {
                mT__33(); 

                }
                break;
            case 13 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:82: T__34
                {
                mT__34(); 

                }
                break;
            case 14 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:88: T__35
                {
                mT__35(); 

                }
                break;
            case 15 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:94: T__36
                {
                mT__36(); 

                }
                break;
            case 16 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:100: T__37
                {
                mT__37(); 

                }
                break;
            case 17 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:106: T__38
                {
                mT__38(); 

                }
                break;
            case 18 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:112: T__39
                {
                mT__39(); 

                }
                break;
            case 19 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:118: T__40
                {
                mT__40(); 

                }
                break;
            case 20 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:124: T__41
                {
                mT__41(); 

                }
                break;
            case 21 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:130: T__42
                {
                mT__42(); 

                }
                break;
            case 22 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:136: T__43
                {
                mT__43(); 

                }
                break;
            case 23 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:142: T__44
                {
                mT__44(); 

                }
                break;
            case 24 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:148: T__45
                {
                mT__45(); 

                }
                break;
            case 25 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:154: T__46
                {
                mT__46(); 

                }
                break;
            case 26 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:160: T__47
                {
                mT__47(); 

                }
                break;
            case 27 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:166: T__48
                {
                mT__48(); 

                }
                break;
            case 28 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:172: T__49
                {
                mT__49(); 

                }
                break;
            case 29 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:178: T__50
                {
                mT__50(); 

                }
                break;
            case 30 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:184: T__51
                {
                mT__51(); 

                }
                break;
            case 31 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:190: T__52
                {
                mT__52(); 

                }
                break;
            case 32 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:196: T__53
                {
                mT__53(); 

                }
                break;
            case 33 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:202: T__54
                {
                mT__54(); 

                }
                break;
            case 34 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:208: T__55
                {
                mT__55(); 

                }
                break;
            case 35 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:214: T__56
                {
                mT__56(); 

                }
                break;
            case 36 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:220: T__57
                {
                mT__57(); 

                }
                break;
            case 37 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:226: T__58
                {
                mT__58(); 

                }
                break;
            case 38 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:232: T__59
                {
                mT__59(); 

                }
                break;
            case 39 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:238: T__60
                {
                mT__60(); 

                }
                break;
            case 40 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:244: T__61
                {
                mT__61(); 

                }
                break;
            case 41 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:250: T__62
                {
                mT__62(); 

                }
                break;
            case 42 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:256: T__63
                {
                mT__63(); 

                }
                break;
            case 43 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:262: T__64
                {
                mT__64(); 

                }
                break;
            case 44 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:268: T__65
                {
                mT__65(); 

                }
                break;
            case 45 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:274: T__66
                {
                mT__66(); 

                }
                break;
            case 46 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:280: T__67
                {
                mT__67(); 

                }
                break;
            case 47 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:286: T__68
                {
                mT__68(); 

                }
                break;
            case 48 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:292: T__69
                {
                mT__69(); 

                }
                break;
            case 49 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:298: T__70
                {
                mT__70(); 

                }
                break;
            case 50 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:304: T__71
                {
                mT__71(); 

                }
                break;
            case 51 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:310: T__72
                {
                mT__72(); 

                }
                break;
            case 52 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:316: T__73
                {
                mT__73(); 

                }
                break;
            case 53 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:322: T__74
                {
                mT__74(); 

                }
                break;
            case 54 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:328: T__75
                {
                mT__75(); 

                }
                break;
            case 55 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:334: T__76
                {
                mT__76(); 

                }
                break;
            case 56 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:340: T__77
                {
                mT__77(); 

                }
                break;
            case 57 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:346: T__78
                {
                mT__78(); 

                }
                break;
            case 58 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:352: T__79
                {
                mT__79(); 

                }
                break;
            case 59 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:358: T__80
                {
                mT__80(); 

                }
                break;
            case 60 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:364: T__81
                {
                mT__81(); 

                }
                break;
            case 61 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:370: T__82
                {
                mT__82(); 

                }
                break;
            case 62 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:376: T__83
                {
                mT__83(); 

                }
                break;
            case 63 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:382: T__84
                {
                mT__84(); 

                }
                break;
            case 64 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:388: T__85
                {
                mT__85(); 

                }
                break;
            case 65 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:394: T__86
                {
                mT__86(); 

                }
                break;
            case 66 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:400: T__87
                {
                mT__87(); 

                }
                break;
            case 67 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:406: T__88
                {
                mT__88(); 

                }
                break;
            case 68 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:412: T__89
                {
                mT__89(); 

                }
                break;
            case 69 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:418: RULE_EXTERNAL_CODE
                {
                mRULE_EXTERNAL_CODE(); 

                }
                break;
            case 70 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:437: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 71 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:449: RULE_BOOLEAN
                {
                mRULE_BOOLEAN(); 

                }
                break;
            case 72 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:462: RULE_INTEGER
                {
                mRULE_INTEGER(); 

                }
                break;
            case 73 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:475: RULE_FLOAT
                {
                mRULE_FLOAT(); 

                }
                break;
            case 74 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:486: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 75 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:502: RULE_BEGIN
                {
                mRULE_BEGIN(); 

                }
                break;
            case 76 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:513: RULE_END
                {
                mRULE_END(); 

                }
                break;
            case 77 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:522: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 78 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:530: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 79 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:539: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 80 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:555: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 81 :
                // ../org.ddmore.mdl/src-gen/org/ddmore/mdl/parser/antlr/internal/InternalMdl.g:1:563: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


    protected DFA14 dfa14 = new DFA14(this);
    protected DFA25 dfa25 = new DFA25(this);
    static final String DFA14_eotS =
        "\1\uffff\1\6\5\uffff";
    static final String DFA14_eofS =
        "\7\uffff";
    static final String DFA14_minS =
        "\2\56\2\uffff\1\56\2\uffff";
    static final String DFA14_maxS =
        "\1\71\1\145\2\uffff\1\145\2\uffff";
    static final String DFA14_acceptS =
        "\2\uffff\1\2\1\3\1\uffff\1\1\1\4";
    static final String DFA14_specialS =
        "\7\uffff}>";
    static final String[] DFA14_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\5\1\uffff\12\4\13\uffff\1\3\37\uffff\1\3",
            "",
            "",
            "\1\5\1\uffff\12\4\13\uffff\1\3\37\uffff\1\3",
            "",
            ""
    };

    static final short[] DFA14_eot = DFA.unpackEncodedString(DFA14_eotS);
    static final short[] DFA14_eof = DFA.unpackEncodedString(DFA14_eofS);
    static final char[] DFA14_min = DFA.unpackEncodedStringToUnsignedChars(DFA14_minS);
    static final char[] DFA14_max = DFA.unpackEncodedStringToUnsignedChars(DFA14_maxS);
    static final short[] DFA14_accept = DFA.unpackEncodedString(DFA14_acceptS);
    static final short[] DFA14_special = DFA.unpackEncodedString(DFA14_specialS);
    static final short[][] DFA14_transition;

    static {
        int numStates = DFA14_transitionS.length;
        DFA14_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA14_transition[i] = DFA.unpackEncodedString(DFA14_transitionS[i]);
        }
    }

    class DFA14 extends DFA {

        public DFA14(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 14;
            this.eot = DFA14_eot;
            this.eof = DFA14_eof;
            this.min = DFA14_min;
            this.max = DFA14_max;
            this.accept = DFA14_accept;
            this.special = DFA14_special;
            this.transition = DFA14_transition;
        }
        public String getDescription() {
            return "5482:14: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( RULE_EXPONENT )? | '.' ( '0' .. '9' )+ ( RULE_EXPONENT )? | ( '0' .. '9' )+ RULE_EXPONENT | '0' .. '9' )";
        }
    }
    static final String DFA25_eotS =
        "\1\uffff\1\71\22\73\2\uffff\1\134\1\73\1\uffff\2\73\1\uffff\2\73"+
        "\1\uffff\2\73\2\uffff\1\154\2\uffff\2\67\1\162\1\164\1\165\1\167"+
        "\1\171\2\uffff\1\67\2\176\10\uffff\1\73\1\uffff\36\73\4\uffff\2"+
        "\73\1\uffff\2\73\1\uffff\2\73\1\uffff\1\u00ab\1\73\24\uffff\1\u00ad"+
        "\1\uffff\1\176\4\uffff\26\73\1\u00c4\5\73\1\u00ca\11\73\1\u00d4"+
        "\1\uffff\1\73\1\uffff\3\73\1\u00d9\2\73\1\u00dc\17\73\1\uffff\3"+
        "\73\1\u00ef\1\73\1\uffff\1\u00f1\6\73\1\u00f8\1\u00f9\1\uffff\1"+
        "\u00fa\3\73\1\uffff\2\73\1\uffff\10\73\1\u0108\11\73\1\uffff\1\73"+
        "\1\uffff\2\73\1\u0115\1\73\1\u00dc\1\73\3\uffff\1\u0118\1\u0119"+
        "\2\73\1\u011c\2\73\1\u011f\2\73\1\u0122\2\73\1\uffff\10\73\1\u012d"+
        "\1\73\1\u012f\1\73\1\uffff\1\73\1\u0132\2\uffff\1\u0133\1\u0134"+
        "\1\uffff\2\73\1\uffff\1\73\1\u0138\1\uffff\12\73\1\uffff\1\73\1"+
        "\uffff\1\u0146\1\73\3\uffff\3\73\1\uffff\1\73\1\u014c\2\73\1\u014f"+
        "\1\73\1\u0151\4\73\1\u0156\1\73\1\uffff\1\u0158\4\73\1\uffff\2\73"+
        "\1\uffff\1\73\1\uffff\4\73\1\uffff\1\73\1\uffff\4\73\1\u016a\1\u016b"+
        "\4\73\1\u0170\1\u0171\5\73\2\uffff\1\u0178\1\73\1\u017a\1\73\2\uffff"+
        "\6\73\1\uffff\1\73\1\uffff\22\73\1\u0195\5\73\1\u019b\1\73\1\uffff"+
        "\1\u019d\3\73\1\u01a1\1\uffff\1\73\1\uffff\3\73\1\uffff\10\73\1"+
        "\u01ae\3\73\1\uffff\1\73\1\u01b3\2\73\1\uffff\1\u01b6\1\73\1\uffff"+
        "\2\73\1\u01ba\1\uffff";
    static final String DFA25_eofS =
        "\u01bb\uffff";
    static final String DFA25_minS =
        "\1\0\1\75\1\144\3\141\1\116\2\101\1\111\1\101\1\102\1\122\1\123"+
        "\1\105\1\111\2\101\1\111\1\154\2\uffff\1\60\1\141\1\uffff\1\115"+
        "\1\125\1\uffff\1\151\1\144\1\uffff\1\146\1\154\2\uffff\1\75\2\uffff"+
        "\1\46\1\174\2\75\1\101\2\52\2\uffff\1\0\2\56\10\uffff\1\154\1\uffff"+
        "\1\162\1\164\1\141\1\163\1\154\1\165\2\104\1\130\1\124\1\116\1\123"+
        "\1\117\1\122\1\115\2\122\1\124\1\123\1\105\1\117\1\124\1\101\1\114"+
        "\1\122\1\114\1\124\1\123\1\102\1\157\4\uffff\1\156\1\154\1\uffff"+
        "\1\124\1\107\1\uffff\1\163\1\145\1\uffff\1\60\1\163\24\uffff\1\56"+
        "\1\uffff\1\56\4\uffff\2\157\1\141\1\147\1\153\1\157\1\145\1\111"+
        "\1\125\1\111\1\105\1\124\1\114\1\104\1\103\1\104\2\125\1\111\1\102"+
        "\1\120\1\105\1\60\1\125\1\111\1\104\1\105\1\101\1\60\1\101\1\111"+
        "\1\122\2\143\1\163\1\122\1\123\1\164\1\60\1\uffff\1\145\1\uffff"+
        "\2\142\1\157\1\60\1\157\1\142\1\60\1\126\1\124\1\116\1\114\1\122"+
        "\1\101\1\117\1\122\1\105\1\103\1\114\2\101\1\125\1\122\1\uffff\1"+
        "\120\1\115\1\105\1\60\1\115\1\uffff\1\60\1\107\1\101\1\153\1\164"+
        "\1\145\1\101\2\60\1\uffff\1\60\2\152\1\142\1\uffff\1\142\1\152\1"+
        "\uffff\1\111\1\137\1\105\1\137\1\101\1\102\1\115\1\111\1\60\1\124"+
        "\1\101\1\102\2\124\1\126\1\137\1\101\1\122\1\uffff\1\105\1\uffff"+
        "\1\116\1\122\1\60\1\151\1\60\1\116\3\uffff\2\60\2\152\1\60\1\104"+
        "\1\126\1\60\1\120\1\116\1\60\1\137\1\120\1\uffff\1\125\1\124\2\111"+
        "\1\137\1\101\1\126\1\124\1\60\1\124\1\60\1\131\1\uffff\1\157\1\60"+
        "\2\uffff\2\60\1\uffff\1\125\1\101\1\uffff\1\122\1\60\1\uffff\1\126"+
        "\1\124\1\122\1\105\1\114\1\115\1\126\1\124\1\101\1\105\1\uffff\1"+
        "\105\1\uffff\1\60\1\156\3\uffff\1\101\1\122\1\105\1\uffff\1\101"+
        "\1\60\1\101\1\117\1\60\1\111\1\60\1\101\1\111\1\122\1\117\1\60\1"+
        "\122\1\uffff\1\60\1\114\1\111\1\104\1\122\1\uffff\1\114\1\116\1"+
        "\uffff\1\124\1\uffff\1\122\1\117\1\111\1\116\1\uffff\1\123\1\uffff"+
        "\1\137\1\101\2\111\2\60\1\131\1\111\1\116\1\101\2\60\1\126\1\102"+
        "\1\103\1\101\1\120\2\uffff\1\60\1\101\1\60\1\102\2\uffff\1\101\1"+
        "\114\1\124\1\102\1\101\1\120\1\uffff\1\102\1\uffff\1\114\1\122\1"+
        "\105\1\111\1\114\1\122\1\101\1\114\1\105\1\111\1\123\1\117\1\105"+
        "\1\101\1\122\1\105\1\123\1\101\1\60\1\116\1\137\1\115\1\101\1\123"+
        "\1\60\1\102\1\uffff\1\60\1\104\1\105\1\115\1\60\1\uffff\1\114\1"+
        "\uffff\1\105\1\124\1\105\1\uffff\1\105\1\106\1\105\1\124\1\123\1"+
        "\111\1\122\1\105\1\60\1\116\1\123\1\122\1\uffff\1\111\1\60\1\123"+
        "\1\124\1\uffff\1\60\1\111\1\uffff\1\117\1\116\1\60\1\uffff";
    static final String DFA25_maxS =
        "\1\uffff\1\75\1\144\1\141\1\151\1\162\1\116\1\117\1\123\1\124\1"+
        "\105\1\125\1\122\1\123\1\105\1\111\1\115\1\105\1\111\1\154\2\uffff"+
        "\1\71\1\165\1\uffff\1\115\1\125\1\uffff\1\151\1\144\1\uffff\1\146"+
        "\1\154\2\uffff\1\75\2\uffff\1\46\1\174\2\75\1\172\2\52\2\uffff\1"+
        "\uffff\2\145\10\uffff\1\154\1\uffff\1\162\1\164\1\141\1\163\1\154"+
        "\1\165\1\120\1\104\1\130\1\124\1\116\1\123\1\117\1\122\1\115\2\122"+
        "\1\124\1\123\1\105\1\117\1\124\1\101\1\114\1\122\1\114\1\124\1\123"+
        "\1\102\1\157\4\uffff\1\156\1\154\1\uffff\1\124\1\107\1\uffff\1\163"+
        "\1\145\1\uffff\1\172\1\163\24\uffff\1\145\1\uffff\1\145\4\uffff"+
        "\2\157\1\141\1\147\1\153\1\157\1\145\1\111\1\125\1\111\1\105\1\124"+
        "\1\114\1\104\1\103\1\104\2\125\1\111\1\102\1\120\1\105\1\172\1\125"+
        "\1\111\1\104\1\105\1\101\1\172\1\101\1\111\1\122\2\143\1\163\1\122"+
        "\1\123\1\164\1\172\1\uffff\1\145\1\uffff\2\142\1\157\1\172\1\157"+
        "\1\142\1\172\1\126\1\124\1\116\1\114\1\122\1\101\1\117\1\122\1\105"+
        "\1\103\1\114\2\101\1\125\1\122\1\uffff\1\120\1\115\1\105\1\172\1"+
        "\115\1\uffff\1\172\1\107\1\101\1\153\1\164\1\145\1\101\2\172\1\uffff"+
        "\1\172\2\152\1\142\1\uffff\1\142\1\152\1\uffff\1\111\1\137\1\105"+
        "\1\137\1\101\1\102\1\115\1\111\1\172\1\124\1\101\1\102\2\124\1\126"+
        "\1\137\1\101\1\122\1\uffff\1\105\1\uffff\1\116\1\122\1\172\1\151"+
        "\1\172\1\116\3\uffff\2\172\2\152\1\172\1\104\1\126\1\172\1\120\1"+
        "\116\1\172\1\137\1\120\1\uffff\1\125\1\124\2\111\1\137\1\101\1\126"+
        "\1\124\1\172\1\124\1\172\1\131\1\uffff\1\157\1\172\2\uffff\2\172"+
        "\1\uffff\1\125\1\101\1\uffff\1\122\1\172\1\uffff\1\126\1\124\1\122"+
        "\1\111\1\114\1\115\1\126\1\124\1\101\1\111\1\uffff\1\105\1\uffff"+
        "\1\172\1\156\3\uffff\1\101\1\122\1\105\1\uffff\1\101\1\172\1\101"+
        "\1\117\1\172\1\111\1\172\1\101\1\111\1\122\1\117\1\172\1\122\1\uffff"+
        "\1\172\1\114\1\111\1\104\1\122\1\uffff\1\114\1\116\1\uffff\1\124"+
        "\1\uffff\1\122\1\117\1\111\1\116\1\uffff\1\123\1\uffff\1\137\1\101"+
        "\2\111\2\172\1\131\1\111\1\116\1\101\2\172\1\126\1\102\1\103\1\101"+
        "\1\120\2\uffff\1\172\1\101\1\172\1\102\2\uffff\1\101\1\114\1\124"+
        "\1\102\1\101\1\120\1\uffff\1\102\1\uffff\1\114\1\122\1\105\1\111"+
        "\1\114\1\122\1\101\1\114\1\105\1\111\1\123\1\117\1\105\1\101\1\122"+
        "\1\105\1\123\1\101\1\172\1\116\1\137\1\115\1\101\1\123\1\172\1\102"+
        "\1\uffff\1\172\1\104\1\105\1\115\1\172\1\uffff\1\114\1\uffff\1\105"+
        "\1\124\1\105\1\uffff\1\105\1\106\1\105\1\124\1\123\1\111\1\122\1"+
        "\105\1\172\1\116\1\123\1\122\1\uffff\1\111\1\172\1\123\1\124\1\uffff"+
        "\1\172\1\111\1\uffff\1\117\1\116\1\172\1\uffff";
    static final String DFA25_acceptS =
        "\24\uffff\1\34\1\35\2\uffff\1\45\2\uffff\1\55\2\uffff\1\60\2\uffff"+
        "\1\63\1\64\1\uffff\1\66\1\67\7\uffff\1\103\1\104\3\uffff\1\112\1"+
        "\113\1\114\1\115\1\120\1\121\1\76\1\1\1\uffff\1\115\36\uffff\1\34"+
        "\1\35\1\41\1\111\2\uffff\1\45\2\uffff\1\55\2\uffff\1\60\2\uffff"+
        "\1\63\1\64\1\77\1\65\1\66\1\67\1\70\1\71\1\74\1\72\1\75\1\73\1\100"+
        "\1\105\1\101\1\117\1\102\1\103\1\104\1\106\1\uffff\1\110\1\uffff"+
        "\1\112\1\113\1\114\1\120\47\uffff\1\61\1\uffff\1\116\26\uffff\1"+
        "\31\5\uffff\1\51\11\uffff\1\57\4\uffff\1\33\2\uffff\1\107\22\uffff"+
        "\1\25\1\uffff\1\27\6\uffff\1\52\1\56\1\62\15\uffff\1\53\14\uffff"+
        "\1\32\2\uffff\1\2\1\3\2\uffff\1\6\2\uffff\1\36\2\uffff\1\54\12\uffff"+
        "\1\24\1\uffff\1\37\2\uffff\1\47\1\4\1\5\3\uffff\1\50\15\uffff\1"+
        "\30\5\uffff\1\40\2\uffff\1\44\1\uffff\1\46\4\uffff\1\43\1\uffff"+
        "\1\42\21\uffff\1\22\1\21\4\uffff\1\20\1\26\6\uffff\1\23\1\uffff"+
        "\1\17\32\uffff\1\12\5\uffff\1\16\1\uffff\1\10\3\uffff\1\15\14\uffff"+
        "\1\7\4\uffff\1\13\2\uffff\1\14\3\uffff\1\11";
    static final String DFA25_specialS =
        "\1\0\56\uffff\1\1\u018b\uffff}>";
    static final String[] DFA25_transitionS = {
            "\11\67\2\66\2\67\1\66\22\67\1\66\1\43\1\57\1\62\1\67\1\55\1"+
            "\46\1\67\1\24\1\25\1\53\1\33\1\30\1\56\1\26\1\54\1\60\11\61"+
            "\1\42\1\67\1\50\1\1\1\51\1\41\1\67\1\65\1\32\1\65\1\21\1\15"+
            "\1\17\1\14\1\16\1\6\2\65\1\22\1\7\1\31\1\13\1\20\1\65\1\10\1"+
            "\11\2\65\1\12\4\65\1\44\1\67\1\45\1\52\1\65\1\67\1\65\1\23\1"+
            "\65\1\4\1\40\1\27\2\65\1\37\2\65\1\34\1\2\1\65\1\35\1\3\3\65"+
            "\1\5\6\65\1\63\1\47\1\64\1\36\uff81\67",
            "\1\70",
            "\1\72",
            "\1\74",
            "\1\75\7\uffff\1\76",
            "\1\77\3\uffff\1\100\14\uffff\1\101",
            "\1\102",
            "\1\105\12\uffff\1\104\2\uffff\1\103",
            "\1\106\1\uffff\1\110\17\uffff\1\107",
            "\1\112\12\uffff\1\111",
            "\1\113\3\uffff\1\114",
            "\1\116\1\uffff\1\117\20\uffff\1\115",
            "\1\120",
            "\1\121",
            "\1\122",
            "\1\123",
            "\1\124\13\uffff\1\125",
            "\1\126\3\uffff\1\127",
            "\1\130",
            "\1\131",
            "",
            "",
            "\12\135",
            "\1\137\23\uffff\1\136",
            "",
            "\1\141",
            "\1\142",
            "",
            "\1\144",
            "\1\145",
            "",
            "\1\147",
            "\1\150",
            "",
            "",
            "\1\153",
            "",
            "",
            "\1\157",
            "\1\160",
            "\1\161",
            "\1\163",
            "\32\73\4\uffff\1\73\1\uffff\32\73",
            "\1\166",
            "\1\170",
            "",
            "",
            "\0\174",
            "\1\135\1\uffff\12\175\13\uffff\1\135\37\uffff\1\135",
            "\1\135\1\uffff\12\177\13\uffff\1\135\37\uffff\1\135",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\u0084",
            "",
            "\1\u0085",
            "\1\u0086",
            "\1\u0087",
            "\1\u0088",
            "\1\u0089",
            "\1\u008a",
            "\1\u008b\7\uffff\1\u008d\3\uffff\1\u008c",
            "\1\u008e",
            "\1\u008f",
            "\1\u0090",
            "\1\u0091",
            "\1\u0092",
            "\1\u0093",
            "\1\u0094",
            "\1\u0095",
            "\1\u0096",
            "\1\u0097",
            "\1\u0098",
            "\1\u0099",
            "\1\u009a",
            "\1\u009b",
            "\1\u009c",
            "\1\u009d",
            "\1\u009e",
            "\1\u009f",
            "\1\u00a0",
            "\1\u00a1",
            "\1\u00a2",
            "\1\u00a3",
            "\1\u00a4",
            "",
            "",
            "",
            "",
            "\1\u00a5",
            "\1\u00a6",
            "",
            "\1\u00a7",
            "\1\u00a8",
            "",
            "\1\u00a9",
            "\1\u00aa",
            "",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\1\u00ac",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\135\1\uffff\12\175\13\uffff\1\135\37\uffff\1\135",
            "",
            "\1\135\1\uffff\12\177\13\uffff\1\135\37\uffff\1\135",
            "",
            "",
            "",
            "",
            "\1\u00ae",
            "\1\u00af",
            "\1\u00b0",
            "\1\u00b1",
            "\1\u00b2",
            "\1\u00b3",
            "\1\u00b4",
            "\1\u00b5",
            "\1\u00b6",
            "\1\u00b7",
            "\1\u00b8",
            "\1\u00b9",
            "\1\u00ba",
            "\1\u00bb",
            "\1\u00bc",
            "\1\u00bd",
            "\1\u00be",
            "\1\u00bf",
            "\1\u00c0",
            "\1\u00c1",
            "\1\u00c2",
            "\1\u00c3",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\1\u00c5",
            "\1\u00c6",
            "\1\u00c7",
            "\1\u00c8",
            "\1\u00c9",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\1\u00cb",
            "\1\u00cc",
            "\1\u00cd",
            "\1\u00ce",
            "\1\u00cf",
            "\1\u00d0",
            "\1\u00d1",
            "\1\u00d2",
            "\1\u00d3",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "",
            "\1\u00d5",
            "",
            "\1\u00d6",
            "\1\u00d7",
            "\1\u00d8",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\1\u00da",
            "\1\u00db",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\1\u00dd",
            "\1\u00de",
            "\1\u00df",
            "\1\u00e0",
            "\1\u00e1",
            "\1\u00e2",
            "\1\u00e3",
            "\1\u00e4",
            "\1\u00e5",
            "\1\u00e6",
            "\1\u00e7",
            "\1\u00e8",
            "\1\u00e9",
            "\1\u00ea",
            "\1\u00eb",
            "",
            "\1\u00ec",
            "\1\u00ed",
            "\1\u00ee",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\1\u00f0",
            "",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\1\u00f2",
            "\1\u00f3",
            "\1\u00f4",
            "\1\u00f5",
            "\1\u00f6",
            "\1\u00f7",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\1\u00fb",
            "\1\u00fc",
            "\1\u00fd",
            "",
            "\1\u00fe",
            "\1\u00ff",
            "",
            "\1\u0100",
            "\1\u0101",
            "\1\u0102",
            "\1\u0103",
            "\1\u0104",
            "\1\u0105",
            "\1\u0106",
            "\1\u0107",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\1\u0109",
            "\1\u010a",
            "\1\u010b",
            "\1\u010c",
            "\1\u010d",
            "\1\u010e",
            "\1\u010f",
            "\1\u0110",
            "\1\u0111",
            "",
            "\1\u0112",
            "",
            "\1\u0113",
            "\1\u0114",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\1\u0116",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\1\u0117",
            "",
            "",
            "",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\1\u011a",
            "\1\u011b",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\1\u011d",
            "\1\u011e",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\1\u0120",
            "\1\u0121",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\1\u0123",
            "\1\u0124",
            "",
            "\1\u0125",
            "\1\u0126",
            "\1\u0127",
            "\1\u0128",
            "\1\u0129",
            "\1\u012a",
            "\1\u012b",
            "\1\u012c",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\1\u012e",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\1\u0130",
            "",
            "\1\u0131",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "",
            "",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "",
            "\1\u0135",
            "\1\u0136",
            "",
            "\1\u0137",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "",
            "\1\u0139",
            "\1\u013a",
            "\1\u013b",
            "\1\u013d\3\uffff\1\u013c",
            "\1\u013e",
            "\1\u013f",
            "\1\u0140",
            "\1\u0141",
            "\1\u0142",
            "\1\u0144\3\uffff\1\u0143",
            "",
            "\1\u0145",
            "",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\1\u0147",
            "",
            "",
            "",
            "\1\u0148",
            "\1\u0149",
            "\1\u014a",
            "",
            "\1\u014b",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\1\u014d",
            "\1\u014e",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\1\u0150",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\1\u0152",
            "\1\u0153",
            "\1\u0154",
            "\1\u0155",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\1\u0157",
            "",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\1\u0159",
            "\1\u015a",
            "\1\u015b",
            "\1\u015c",
            "",
            "\1\u015d",
            "\1\u015e",
            "",
            "\1\u015f",
            "",
            "\1\u0160",
            "\1\u0161",
            "\1\u0162",
            "\1\u0163",
            "",
            "\1\u0164",
            "",
            "\1\u0165",
            "\1\u0166",
            "\1\u0167",
            "\1\u0168",
            "\12\73\7\uffff\32\73\4\uffff\1\u0169\1\uffff\32\73",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\1\u016c",
            "\1\u016d",
            "\1\u016e",
            "\1\u016f",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\1\u0172",
            "\1\u0173",
            "\1\u0174",
            "\1\u0175",
            "\1\u0176",
            "",
            "",
            "\12\73\7\uffff\32\73\4\uffff\1\u0177\1\uffff\32\73",
            "\1\u0179",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\1\u017b",
            "",
            "",
            "\1\u017c",
            "\1\u017d",
            "\1\u017e",
            "\1\u017f",
            "\1\u0180",
            "\1\u0181",
            "",
            "\1\u0182",
            "",
            "\1\u0183",
            "\1\u0184",
            "\1\u0185",
            "\1\u0186",
            "\1\u0187",
            "\1\u0188",
            "\1\u0189",
            "\1\u018a",
            "\1\u018b",
            "\1\u018c",
            "\1\u018d",
            "\1\u018e",
            "\1\u018f",
            "\1\u0190",
            "\1\u0191",
            "\1\u0192",
            "\1\u0193",
            "\1\u0194",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\1\u0196",
            "\1\u0197",
            "\1\u0198",
            "\1\u0199",
            "\1\u019a",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\1\u019c",
            "",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\1\u019e",
            "\1\u019f",
            "\1\u01a0",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "",
            "\1\u01a2",
            "",
            "\1\u01a3",
            "\1\u01a4",
            "\1\u01a5",
            "",
            "\1\u01a6",
            "\1\u01a7",
            "\1\u01a8",
            "\1\u01a9",
            "\1\u01aa",
            "\1\u01ab",
            "\1\u01ac",
            "\1\u01ad",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\1\u01af",
            "\1\u01b0",
            "\1\u01b1",
            "",
            "\1\u01b2",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\1\u01b4",
            "\1\u01b5",
            "",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            "\1\u01b7",
            "",
            "\1\u01b8",
            "\1\u01b9",
            "\12\73\7\uffff\32\73\4\uffff\1\73\1\uffff\32\73",
            ""
    };

    static final short[] DFA25_eot = DFA.unpackEncodedString(DFA25_eotS);
    static final short[] DFA25_eof = DFA.unpackEncodedString(DFA25_eofS);
    static final char[] DFA25_min = DFA.unpackEncodedStringToUnsignedChars(DFA25_minS);
    static final char[] DFA25_max = DFA.unpackEncodedStringToUnsignedChars(DFA25_maxS);
    static final short[] DFA25_accept = DFA.unpackEncodedString(DFA25_acceptS);
    static final short[] DFA25_special = DFA.unpackEncodedString(DFA25_specialS);
    static final short[][] DFA25_transition;

    static {
        int numStates = DFA25_transitionS.length;
        DFA25_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA25_transition[i] = DFA.unpackEncodedString(DFA25_transitionS[i]);
        }
    }

    class DFA25 extends DFA {

        public DFA25(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 25;
            this.eot = DFA25_eot;
            this.eof = DFA25_eof;
            this.min = DFA25_min;
            this.max = DFA25_max;
            this.accept = DFA25_accept;
            this.special = DFA25_special;
            this.transition = DFA25_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | RULE_EXTERNAL_CODE | RULE_STRING | RULE_BOOLEAN | RULE_INTEGER | RULE_FLOAT | RULE_SL_COMMENT | RULE_BEGIN | RULE_END | RULE_ID | RULE_INT | RULE_ML_COMMENT | RULE_WS | RULE_ANY_OTHER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA25_0 = input.LA(1);

                        s = -1;
                        if ( (LA25_0=='=') ) {s = 1;}

                        else if ( (LA25_0=='m') ) {s = 2;}

                        else if ( (LA25_0=='p') ) {s = 3;}

                        else if ( (LA25_0=='d') ) {s = 4;}

                        else if ( (LA25_0=='t') ) {s = 5;}

                        else if ( (LA25_0=='I') ) {s = 6;}

                        else if ( (LA25_0=='M') ) {s = 7;}

                        else if ( (LA25_0=='R') ) {s = 8;}

                        else if ( (LA25_0=='S') ) {s = 9;}

                        else if ( (LA25_0=='V') ) {s = 10;}

                        else if ( (LA25_0=='O') ) {s = 11;}

                        else if ( (LA25_0=='G') ) {s = 12;}

                        else if ( (LA25_0=='E') ) {s = 13;}

                        else if ( (LA25_0=='H') ) {s = 14;}

                        else if ( (LA25_0=='F') ) {s = 15;}

                        else if ( (LA25_0=='P') ) {s = 16;}

                        else if ( (LA25_0=='D') ) {s = 17;}

                        else if ( (LA25_0=='L') ) {s = 18;}

                        else if ( (LA25_0=='b') ) {s = 19;}

                        else if ( (LA25_0=='(') ) {s = 20;}

                        else if ( (LA25_0==')') ) {s = 21;}

                        else if ( (LA25_0=='.') ) {s = 22;}

                        else if ( (LA25_0=='f') ) {s = 23;}

                        else if ( (LA25_0==',') ) {s = 24;}

                        else if ( (LA25_0=='N') ) {s = 25;}

                        else if ( (LA25_0=='B') ) {s = 26;}

                        else if ( (LA25_0=='+') ) {s = 27;}

                        else if ( (LA25_0=='l') ) {s = 28;}

                        else if ( (LA25_0=='o') ) {s = 29;}

                        else if ( (LA25_0=='~') ) {s = 30;}

                        else if ( (LA25_0=='i') ) {s = 31;}

                        else if ( (LA25_0=='e') ) {s = 32;}

                        else if ( (LA25_0=='?') ) {s = 33;}

                        else if ( (LA25_0==':') ) {s = 34;}

                        else if ( (LA25_0=='!') ) {s = 35;}

                        else if ( (LA25_0=='[') ) {s = 36;}

                        else if ( (LA25_0==']') ) {s = 37;}

                        else if ( (LA25_0=='&') ) {s = 38;}

                        else if ( (LA25_0=='|') ) {s = 39;}

                        else if ( (LA25_0=='<') ) {s = 40;}

                        else if ( (LA25_0=='>') ) {s = 41;}

                        else if ( (LA25_0=='^') ) {s = 42;}

                        else if ( (LA25_0=='*') ) {s = 43;}

                        else if ( (LA25_0=='/') ) {s = 44;}

                        else if ( (LA25_0=='%') ) {s = 45;}

                        else if ( (LA25_0=='-') ) {s = 46;}

                        else if ( (LA25_0=='\"') ) {s = 47;}

                        else if ( (LA25_0=='0') ) {s = 48;}

                        else if ( ((LA25_0>='1' && LA25_0<='9')) ) {s = 49;}

                        else if ( (LA25_0=='#') ) {s = 50;}

                        else if ( (LA25_0=='{') ) {s = 51;}

                        else if ( (LA25_0=='}') ) {s = 52;}

                        else if ( (LA25_0=='A'||LA25_0=='C'||(LA25_0>='J' && LA25_0<='K')||LA25_0=='Q'||(LA25_0>='T' && LA25_0<='U')||(LA25_0>='W' && LA25_0<='Z')||LA25_0=='_'||LA25_0=='a'||LA25_0=='c'||(LA25_0>='g' && LA25_0<='h')||(LA25_0>='j' && LA25_0<='k')||LA25_0=='n'||(LA25_0>='q' && LA25_0<='s')||(LA25_0>='u' && LA25_0<='z')) ) {s = 53;}

                        else if ( ((LA25_0>='\t' && LA25_0<='\n')||LA25_0=='\r'||LA25_0==' ') ) {s = 54;}

                        else if ( ((LA25_0>='\u0000' && LA25_0<='\b')||(LA25_0>='\u000B' && LA25_0<='\f')||(LA25_0>='\u000E' && LA25_0<='\u001F')||LA25_0=='$'||LA25_0=='\''||LA25_0==';'||LA25_0=='@'||LA25_0=='\\'||LA25_0=='`'||(LA25_0>='\u007F' && LA25_0<='\uFFFF')) ) {s = 55;}

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA25_47 = input.LA(1);

                        s = -1;
                        if ( ((LA25_47>='\u0000' && LA25_47<='\uFFFF')) ) {s = 124;}

                        else s = 55;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 25, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}