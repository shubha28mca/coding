# Stack / Monotonic Stack — Quick Notes

## When to reach for it (recognition)
- **Matched / nested** structure (brackets, tags, scopes) → **plain stack** (LIFO).
- **"Next / previous greater or smaller"**, spans, "how far until…", histogram → **monotonic stack**.
- An element's answer is resolved by a **later** element → park it and wait.

## Monotonic stack mechanism
1. Keep the stack monotonic (increasing or decreasing); **store indices** if you need distances.
2. On each arrival: **while** it violates the order → **pop** (each pop is *resolved by* this arrival).
3. Then **push** the arrival.
4. Whatever remains → no answer → default (`-1`).

## Choosing direction
| Want | Stack order |
|------|-------------|
| Next greater | decreasing (pop when arrival is bigger) |
| Next smaller | increasing (pop when arrival is smaller) |

## Why it's O(n) (say this!)
Count ops, not nesting: each index is **pushed once, popped ≤ once** ⇒ total inner-loop work ≤ n ⇒ **amortized O(n)**, O(n) space.

## Complexity — uniform
| Problem | Time | Space |
|---------|------|-------|
| Valid parentheses | O(n) | O(n) |
| Next greater / daily temps | O(n) | O(n) |
| Largest rectangle histogram | O(n) | O(n) |

## FAANG variants to practice
- **Valid Parentheses / Min Stack** — Amazon, Microsoft
- **Daily Temperatures** — Amazon, Google, Meta
- **Next Greater Element I & II** — Amazon, Microsoft
- **Largest Rectangle in Histogram** (hard) — Google, Amazon
- **Trapping Rain Water** — Google, Amazon
- **Asteroid Collision / Simplify Path / Decode String** — Meta, Amazon

## Recognition drill
Nested/matched → plain stack. Next/prev greater/smaller or histogram → monotonic stack. Pick direction, store indices, **pop-while-violates then push**. Quote amortized O(n) with push-once/pop-once.
