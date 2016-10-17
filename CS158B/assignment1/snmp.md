snmp
==============================

snmp command syntax

`snmpaction authentication host oid`

Basic snmp get request

`snmpget -u demo -l authPriv -a SHA -x AES -A snmppassword -X snmppassword localhost 1.3.6.1.2.1.1.1.0`

Authentication information, if not specified in `/etc/snmp/snmp.conf` or `~/.snmp.conf`

`-u demo -l authPriv -a SHA -x AES -A snmppassword -X snmppassword`
