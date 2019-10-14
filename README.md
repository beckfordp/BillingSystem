# BillingSystem

I test drove all the production code (wrote a test before I wrote the code). I didn't check in all the intermediate steps as proof, but hopefully the 
fact that the code was test driven is visible from the class/method  names etc
which read well in the tests.

On my first commit I had missed some tests. I didn't test for orders that contained unknown order items although I had coded for this. Unknown items are ignored.

I also had forgotten the special case of a max service charge of 20 pounds. These additional test are visible in the git history.

I then pondered over whether to simplify the logic for working out the service charge. From the rerquirements, the service charge applicable to each order is always the same as the max service charge applicable to each order item.
This fact isn't stated explicitly, and if I had access to a product owner, this would definately be a queastion I'd ask.

For the purposes of this exercise, I chose to assume that this is indeed always true. It meant that I could simplify the logic and remove a bunch of trait classes.

I was a bit reluctant to loose my traits, since they seemed to model the classification of the different items well, allowing for mixins. In the end I decided that
removing them made the code simpler, and if they were ever needed I could add them back (YAGNI).


