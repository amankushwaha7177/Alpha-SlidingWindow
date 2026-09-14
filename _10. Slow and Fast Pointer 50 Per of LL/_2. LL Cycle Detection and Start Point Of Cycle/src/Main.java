public class Main {
}
/*
        a                 b
HEAD ───────→ LOOP START ───────→ MEETING
               ↑                    │
               │                    │
               └────── c ───────────┘       L = a + c

Keep It Remember    a = c
 */

/*
Mathematics Behind Finding Loop Start :

a = distance from HEAD to Loop Start.
b = distance from Loop Start to First Meeting Point.
c = distance from First Meeting Point back to Loop Start.
L = total length of the cycle.

Therefore:

L = b + c


At the first meeting:
        a                 b
HEAD ───────→ LOOP START ───────→ MEETING
               ↑                    │
               │                    │
               └────── c ───────────┘       L = a + c


1. First Time Slow travels:
    HEAD → LOOP START → MEETING
    => Distance = a + b

2. Fast travels:
    HEAD → LOOP START → MEETING → complete cycle
    => Distance = a + b + L


Because Fast moves twice as fast:
a + b + L = 2(a + b)
a + b + L = 2a + 2b

Therefore:
L = a + b


But we already know:
L = b + c


So:
a + b = b + c


Cancel b:

a = c


THIS IS THE KEY RESULT:
Distance HEAD → LOOP START
=
Distance MEETING → LOOP START



Final Rule:

First Meeting → Proves Cycle Exists.
Reset Slow to Head → Keep Fast at Meeting.
Move Both One Step → Second Meeting is Loop Start.
*/