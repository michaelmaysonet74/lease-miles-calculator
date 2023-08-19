# lease-miles-calculator

## Getting Started

### Local Development

```bash
# Start Server
$ sbt server/run
# ...
# --- (Running the application, auto-reloading is enabled) ---
# [info] p.c.s.AkkaHttpServer - Listening for HTTP on /0:0:0:0:0:0:0:0:8090
# (Server started, use Enter to stop and go back to the console...)

```

### Example Request

```bash
$ curl "http://localhost:8090/lease/term-balance?currentMiles=10854"
#  {
#    "currentMiles": 10854,
#    "balance": {
#      "monthly": 9156,
#      "total": 19161
#    },
#    "lease": {
#      "year": 10,
#      "month": 0,
#      "today": {
#        "date": "8/19/2023",
#        "time": "12:37:42 PM"
#      }
#    }
#  }
```
