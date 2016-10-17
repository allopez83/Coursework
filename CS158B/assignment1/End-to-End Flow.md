End-to-End Flow
==============================

## Agent Configuration

### Community (for version 1)
How is it configured?

Communities are set in the snmp daemon's configuration file, located at /etc/snmp/snmpd.conf. rocommunity specifies which communities the machine belongs to, followed by which IPs that can access the machine. For example, having rocommunity public means that the machine has read access on the public community.

## Management Station Configuration

### Community (for version 1)
How is it configured?

Under "Configure OpenNMS" on the top right corner, there's an option to "Configure SNMP Community Names by IP Address". Here, information about hte community is entered, including the SNMP verseion, range of IP addresses used, and the community name.

### Polling rate
How is it configured?

The polling is set in a config file located at /etc/opennms/poller-configuration.xml, with different intervals and settings for each protocol OpenNMS tracks (ICMP, DNS, FTP, etc). The default interval is 300000 milliseconds, or five minutes.

## Data Handling

### Storing the data
What tool performs it?

OpenNMS comes with a PostgreSQL which stores information about the nodes, requisitions, and other parts of OpenNMS. However, the data about the nodes or machines themselves is stored on the machine and retrieved via SNMP.

### Visualizing the data
What tool performs it?

OpenNMS itself performs the data visualization. It pulls data from PostgreSQL and feeds it into graphing tools that show uptime, ping delays, and other data.
