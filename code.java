import java.util.Scanner;
class Armstrongnumber
{
public static void main(String[] args)
{
Scanner s=new Scanner(System.in);
System.out.println("Enter a  value: ");
int a=s.nextInt();
int temp=a;
int count=iscount(a);
int sum=0;
a=temp;
while(a>0)
{
int rem=a%10;
sum+=isarmstrong(count,rem);
a=a/10;
}
if ( temp==sum)
{
System.out.println("The number "+temp+" is a Armstrongnumber.!");
}
else
{
System.out.println("The number "+temp+" is not an Armstrongnumber.!");
}
}
public static int iscount(int a)
{
int count=0;
while(a>0)
{
count++;
a=a/10;
}
return count;
}
public static int isarmstrong(int count,int lastdigit)
{
int product=1;
for(int i=1;i<=count;i++)
{
product*=lastdigit;
}
return product;
}
}