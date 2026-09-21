public class Main {
}
/*
🧠 The main difference :
------------------------
The while loop does the opposite job depending on whether we need maximum or minimum length.
Maximum length → INVALID → shrink → VALID → calculate maximum.
Minimum length → VALID → calculate minimum → shrink → INVALID.
Maximum means we fix the invalid window, while minimum means we destroy the valid window.

 */


/*
For a MINIMUM answer:

Suppose:
target = 7
arr = [2, 3, 5, 2]
             |
When R reaches 5:
[2, 3, 5]
sum = 10

The window is VALID because 10 >= 7.

Now ask yourself:
"Can I make this window smaller while it is still valid?"

YES.

[2,3,5] → length 3
   ↓ remove 2
[3,5]   → length 2, still valid
   ↓ remove 3
[5]     → length 1, invalid

So the intuition naturally becomes:

VALID
  ↓
"Can I make it smaller?"
  ↓
YES → shrink
  ↓
still VALID?
  ↓
YES → shrink again
  ↓
INVALID → STOP

That's why we use:

while(windowSum >= target)

Compare with MAXIMUM:

For a MAXIMUM answer, once the window is valid, we want to keep it large.
So we do not shrink a valid window unnecessarily.

INVALID
  ↓
"How do I make it valid?"
  ↓
Shrink
  ↓
VALID
  ↓
Record maximum
  ↓
R expands again

Easiest intuition:

MAXIMUM → Fix the bad window → INVALID → VALID → Find maximum.

MINIMUM → Make the good window smaller → VALID → INVALID → Find minimum.

Maximum: make the bad window good.
Minimum: make the good window smaller until it becomes bad.
*/