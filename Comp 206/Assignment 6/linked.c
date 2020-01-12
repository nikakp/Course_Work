// Nika Prairie
// ID: 260843183
#include <stdlib.h>
#include "debug.h"

struct ACCOUNT {
    int accountNumber;
    float balance;
    struct ACCOUNT *next;
};

// head points to the first element
// of the linked list. If it's
// NULL it means that the linked list is
// empty
struct ACCOUNT *head = NULL;

/*
 * PrettyPrint
 *
 * Prints out all the nodes within the linked
 * list starting with the head
 */
void prettyPrint()
{
    
  struct ACCOUNT *tmp = head;
  // Loop through the linked
  // list until we encounter the last element    
  while(NULL != tmp){
      printf("ACCOUNT ID: %5d BALANCE: $ %9.2f\n", 
             tmp->accountNumber, tmp->balance);
      tmp=tmp->next;
  }
    return;
}

/*
 * find
 *
 * This function finds a node in the linked list
 * which corresponds to the account number
 * passed in, and returns a pointer to the node
 * in question. If none is found, NULL
 * is returned.
 *
 * account: The account we are searching for
 *
 * RETURN: NULL if a matching node is not found
 *         otherwise a pointer to the node
 */
static struct ACCOUNT *find(int account) {
    
    struct ACCOUNT *current = head;

    // Loop until we get to the end
    while(NULL != current){
        // If we found the node matching the
        // account return it.
        if(current->accountNumber == account){
            return current;
        }
        // Go to the next node
        current = current->next;
    }
    // Node not found, return NULL
    return NULL;
}

/*
 * findUpdate
 *
 * This function finds a node matching account
 * and increments the balance of the account
 * in the node by amount. If no node exists
 * in the linked list, we create one
 * and assign the account ID and balance
 * based on amount
 *******************************************
 * NOTE: I made amount FLOAT instead of
 * int as otherwise I couldn't get cents
 * tracked as per the request of the assignment
 * instructions
 *******************************************
 *
 * account: The account we are searching for
 *
 * amount: the balance is to be updated using this
 */
void findUpdate(int account, float amount)
{
    struct ACCOUNT *ptr = NULL;
    // Try to find the account in the 
    // linked list  
    ptr = find(account);
    // if ptr is NULL this means we have not found
    // the account
    if (NULL == ptr) {
        // Allocate a node to store the account
        // and add an entry to the linked list
        ptr = malloc(sizeof(struct ACCOUNT));
        // This should never happen, but just in case
        if (NULL == ptr) {
          fprintf(stderr, "Unable to allocate memory for new account [%d], exiting\n",
                  account);
          exit(1);
        }
        ptr->next = head;
//        debug_print("ptr = %p\n", ptr);
        head = ptr;
//        debug_print("head = %p\n", head);
        ptr->accountNumber = account;
        // We set the balance to 0 as it will
        // be updated after the if statement
        ptr->balance = 0;
        debug_print("Added node: account:[%d] ptr:[%p]\n",
                    ptr->accountNumber, ptr);
    }
    // Change the balance by amount
    ptr->balance += amount;
    return;
    
}


