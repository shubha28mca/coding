# Sliding Window — Quick Notes

## When to reach for it (recognition)
- Answer is about a **contiguous subarray / substring** (a window).
- You optimize a **metric** over it: max, min, sum, count, distinctness.
- Adjacent windows **overlap heavily** → update, don't recompute.

## Two flavors
| Flavor | Trigger | Mechanics |
|--------|---------|-----------|
| **Fixed** | size `k` given | add entering `a[r]`, subtract leaving `a[r-k]` |
| **Variable** | size driven by a constraint | **expand** right until invalid, **shrink** left to restore, record answer when valid |

## Why it's O(n)
Both edges are **monotonic** — they only move forward. Each element enters once and leaves at most once ⇒ ≤ 2n moves.

## Complexity
| Case | Time | Space |
|------|------|-------|
| Fixed window (running sum) | O(n) | O(1) |
| Variable window + map | O(n) | O(k)/O(alphabet) |
| Brute force baseline | O(n·k) / O(n²) | O(1) |

## Boundary of the pattern ⚠️
- **Positives only** → growing/shrinking changes the sum monotonically → valid.
- **Negatives present** → adding can decrease the sum → shrink logic breaks.
  - Switch to **prefix sum + monotonic deque** or **prefix sum + TreeMap**.

## FAANG variants to practice
- **Max Sum / Max Average Subarray Size K** — Amazon, Microsoft (fixed)
- **Longest Substring Without Repeating Characters** — Google, Meta, Amazon
- **Minimum Window Substring** (hard) — Meta, Google
- **Longest Repeating Character Replacement** — Amazon, Meta
- **Fruit Into Baskets / At Most K Distinct** — Google
- **Find All Anagrams / Permutation in String** — Microsoft, Amazon

## Recognition drill
Contiguous + metric → window. `k` given → fixed. Constraint-driven → variable (expand, then shrink). Sanity check: does each move change state monotonically?
