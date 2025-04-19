docker exec -id redis bash

set user:1:name sam
scan 0 
keys user*
keys *
del user:1:name
flushdb
# set keys timeout
- This key a expires in 10 seconds 
  - set a b ex 10 
- Extent the time by 60 more secons
  - expire a 60
- Check TTL
  - ttl a
- Expire in a time stamp
  - set a b exat 1624737950
- Expire in millis e.g. 3000 ms
  - set a b px 3000
- Change value but keep ttl
  - set a c keepttl
# edit or add if present or not preset
- Set only if present
  - set a b xx
- Set only if not present
  - set a b nx
# check if key exists
- exists a
# increment / decrement
- incr a
- decr a
- incrby a 20
- decrby a 20
- Increment a float
  - incrbyfloat a .03
