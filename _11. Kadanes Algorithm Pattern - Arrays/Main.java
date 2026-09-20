public class Main {
}

/*
PROBLEM TYPE                    +VE ONLY       +VE + -VE
----------------------------------------------------------------
Longest subarray sum <= K       Sliding        Prefix/Advanced
Count subarrays sum <= K        Sliding        Prefix + Fenwick
Minimum subarray sum >= K       Sliding        Prefix/Advanced
----------------------------------------------------------------
Count subarrays sum = K         Prefix         Prefix
Longest subarray sum = K        Prefix         Prefix
-----------------------------------------------------------------
Range sum L to R                Prefix         Prefix
-----------------------------------------------------------------

Maximum subarray sum             Kadane         Kadane
Minimum subarray sum             Kadane         Kadane
Minimum product  sum             Kadane         Kadane
 */


/*
 The biggest shortcut
 --------------------

If SUM is involved:

+VE ONLY
    ↓
Ask:
"Can I use Sliding Window?"

+VE + -VE
    ↓
Ask:
"Can I use Prefix Sum?"

MAXIMUM/MINIMUM SUBARRAY SUM
    ↓
Think Kadane regardless of +VE/-VE.
*/