public class Main {
}
/*
============================================================
Difference Between AT MOST K and EXACTLY K Distinct
============================================================

1. FRUIT INTO BASKETS = AT MOST 2 DISTINCT

Question:
Find the largest window containing AT MOST 2 distinct fruit types.

Valid conditions:

map.size() < 2  → VALID
map.size() == 2 → VALID
map.size() > 2  → INVALID

Therefore:

map.size() > 2
        ↓
Window becomes INVALID
        ↓
Shrink L using while
        ↓
map.size() <= 2
        ↓
Window becomes VALID
        ↓
Calculate maximum length

Code:

while(map.size() > 2) {
    // Shrink L
}

ans = Math.max(ans, r - l + 1);

Why can we directly calculate ans?

Because after while finishes, the window is guaranteed to contain
at most 2 distinct fruit types, so the window is automatically valid.

Mental Rule:

AT MOST K → Everything up to K is VALID.

1 ✓ → 2 ✓ → 3 ❌


============================================================
2. LARGEST SUBARRAY WITH EXACTLY K DISTINCT

Question:
Find the largest window containing EXACTLY K distinct numbers.

Valid conditions:

map.size() < K  → INVALID
map.size() == K → VALID
map.size() > K  → INVALID

Therefore:

map.size() > K
        ↓
Window becomes INVALID
        ↓
Shrink L using while
        ↓
map.size() == K
        ↓
Window becomes VALID
        ↓
Calculate maximum length

Code:

while(map.size() > k) {
    // Shrink L
}

if(map.size() == k) {
    ans = Math.max(ans, r - l + 1);
}

Why do we need if(map.size() == k)?

Because after shrinking, the window may have exactly K distinct
numbers, but the question requires EXACTLY K, not at most K.

Mental Rule:

EXACTLY K → Only K is VALID.

K-1 ❌ → K ✓ → K+1 ❌


============================================================
3. THE HEAVY-WHILE MOVEMENT IS THE SAME
============================================================

Both problems use the same basic sliding-window movement:

R ENTERS
   ↓
Too many distinct values
   ↓
Window becomes INVALID
   ↓
L → L → L
   ↓
Remove values repeatedly
   ↓
Too many distinct values removed


The difference is only the VALID condition.

AT MOST K:

    map.size() <= K

EXACTLY K:

    map.size() == K


============================================================
4. MOST IMPORTANT INTUITION
============================================================

AT MOST K:

Everything from 0 to K distinct values is valid.

Example K = 2:

0 ✓
1 ✓
2 ✓
3 ❌

Therefore, after fixing map.size() > K,
the window is automatically valid.


EXACTLY K:

Only exactly K distinct values are valid.

Example K = 2:

0 ❌
1 ❌
2 ✓
3 ❌

Therefore, after fixing map.size() > K,
we still need to check:

if(map.size() == k)


============================================================
5. EASY WAY TO REMEMBER
============================================================

AT MOST K:

    map.size() > K → Shrink
    After shrink   → Automatically VALID
    Calculate MAX


EXACTLY K:

    map.size() > K → Shrink
    After shrink   → Check == K
    If == K        → Calculate MAX


So:

AT MOST K  → map.size() <= K
EXACTLY K  → map.size() == K


The Heavy-While shrinking mechanism is the SAME,
but the definition of a VALID window is DIFFERENT.
============================================================
*/