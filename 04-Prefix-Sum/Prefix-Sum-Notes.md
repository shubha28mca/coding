# Prefix Sum — Quick Notes

## When to reach for it (recognition)
- **Many range-sum / range-aggregate queries** on a **static** array.
- Counting subarrays with a **target sum/property**.
- Cue words: "sum between", "number of", "running total", "range".

## Core identity (mind the leading zero)
- `P[0] = 0`, `P[k] = a[0] + … + a[k-1]` → length `n+1`.
- **Sum of `a[i..j]` (inclusive) = `P[j+1] - P[i]`**.
- Build O(n) once → every query O(1).

## Four shapes
| Shape | Use | Cost |
|-------|-----|------|
| 1D prefix | range sum queries | build O(n), query O(1) |
| Prefix + **hash map** | count subarrays sum=k | O(n) / O(n) |
| 2D prefix | submatrix sum (incl-excl) | build O(mn), query O(1) |
| **Difference array** | many range *updates* then read | O(n+q) |

## Prefix + map (Subarray Sum = K)
`P[j+1]-P[i]=k` ⇒ `P[i]=P[j+1]-k`. Seed map `{0:1}`; look up **before** insert.

## 2D inclusion-exclusion
`region = P[r2+1][c2+1] - P[r1][c2+1] - P[r2+1][c1] + P[r1][c1]`

## Boundary ⚠️
- Prefix sum assumes a **static** array.
- Array mutates between queries → **Fenwick (BIT) / segment tree**, O(log n) update+query.
- Use `long` accumulators to avoid overflow.

## FAANG variants to practice
- **Range Sum Query – Immutable (1D/2D)** — Amazon, Microsoft
- **Subarray Sum Equals K** — Meta, Google
- **Contiguous Array (0s/1s)** — Meta, Amazon
- **Product of Array Except Self** — Amazon, Microsoft
- **Corporate Flight Bookings** (diff array) — Google
- **Find Pivot Index** — Amazon

## Recognition drill
Static + range → prefix. Counting subarrays → prefix + map. Range updates → diff array. Mutable → Fenwick/segment tree. Leading zero + `long`.
