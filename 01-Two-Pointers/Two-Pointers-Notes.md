# Two Pointers — Quick Notes

## When to reach for it (recognition)
- Input is **sorted**, or sorting unlocks order-based reasoning.
- You need a **pair / triplet / contiguous window**, not a single element.
- Brute force is **nested loops** over indices → O(n²).

## Two shapes
| Shape | Setup | Moves | Use for |
|-------|-------|-------|---------|
| **Opposite ends** | `left=0`, `right=n-1` | move inward | pair sums, palindrome, max area |
| **Same direction** | `slow=0`, `fast=1..` | fast scans, slow writes | in-place dedup, move zeroes, partition |

## Why moving inward is correct (opposite ends, sorted)
- `sum > target` → the right value is too big with everything → `right--`.
- `sum < target` → the left value is too small with everything → `left++`.
- Each step permanently discards one element ⇒ ≤ n steps ⇒ **O(n)**.

## Complexity
| Case | Time | Space |
|------|------|-------|
| Opposite-ends (sorted) | O(n) | O(1) |
| Slow/fast dedup | O(n) | O(1) |
| Must sort unsorted input first | O(n log n) | O(1)/O(n) by sort |
| Brute force baseline | O(n²) | O(1) |

**Nuance to say out loud:** state cost *with* vs *without* the sort.

## Adaptations
- Unsorted + pair by value → **HashMap** O(n)/O(n) (often beats sorting).
- Unsorted + order logic (3Sum, closest) → **sort then two-pointer**.

## FAANG variants to practice
- **Two Sum II** (sorted) — Amazon, Microsoft
- **3Sum / 3Sum Closest** — Meta, Google
- **Container With Most Water** — Amazon, Meta
- **Trapping Rain Water** (hard) — Google, Amazon
- **Valid Palindrome**, reverse in place — Microsoft, Meta
- **Remove Duplicates / Move Zeroes** — Amazon, Google
