# Binary Search — Quick Notes

## When to reach for it (recognition)
- Search space is **sorted** OR has a **monotonic yes/no predicate** (false…false, true…true).
- You can test the middle and **discard half**.
- Cues: "sorted", "first/last", "minimum/maximum value such that…", huge numeric range, expected **O(log n)**.

## Three templates

**Exact match (inclusive `[lo, hi]`)**
```
while (lo <= hi) { mid = lo+(hi-lo)/2; ... lo=mid+1 / hi=mid-1 }
```

**Lower bound (half-open `[lo, hi)`, hi = n)** ← the one that matters
```
while (lo < hi) { if (pred(mid)) hi = mid; else lo = mid+1; } return lo;
```

**Binary search on the answer**
- Define `feasible(x)` monotonic in x; search the value range.
- e.g. min ship capacity, Koko bananas, split array largest sum.

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| Array search / bound | O(log n) | O(1) |
| Search on answer | O(n·log(range)) | O(1) |

## The 3 classic pitfalls
1. `mid=(lo+hi)/2` **overflows** → use `lo + (hi-lo)/2`.
2. Wrong side update (`mid` vs `mid±1`) → **infinite loop**.
3. Mixing inclusive + half-open in one function.

## FAANG variants to practice
- **Search Insert Position** — Amazon, Microsoft
- **First & Last Position** — Meta, Amazon (lower+upper bound)
- **Search in Rotated Sorted Array** — Google, Meta, Amazon
- **Koko Eating Bananas / Ship Within D Days** — Amazon, Google (search on answer)
- **Median of Two Sorted Arrays** (hard) — Google, Microsoft
- **Find Peak Element** — Meta

## Recognition drill
Sorted/monotonic → binary search. Match → classic. Boundary → lower/upper bound. "min/max such that feasible" → search the answer. Always: safe mid, one-sided shrink, one range convention, state the invariant.
