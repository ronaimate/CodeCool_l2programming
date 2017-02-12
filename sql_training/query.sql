select Name from customers as c, accounts as a where c.CustomerID = a.CustomerID and status = "activated" order by a.balance Desc limit 5; 