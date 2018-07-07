const int MAXSIZE = 10000;

int arr[ MAXSIZE ];
int cur[ MAXSIZE ];

int main ()
{
   int n; // the size of array
   // read n and the array

   cin >> n;
   for( int i = 0; i < n; ++i )
      cin >> arr[ i ];

   int k = 0;
   for( int i = 0; i < n; ++i )
   {
      if( cur[ arr[ i ] ] == 0 )
         ++k;
      ++cur[ arr[ i ] ];
   }

   // now k is the number of distinct elements

   memset( cur, 0, sizeof( cur )); // we need this array anew
   int begin = 0, end = -1; // to make it 0 after first increment
   int best = -1; // best answer currently found
   int ansbegin, ansend; // interval of the best answer currently found
   int cnt = 0; // distinct elements in current subsequence

   while(1)
   {
      if( cnt < k )
      {
         ++end;
         if( end == n )
            break;
         if( cur[ arr[ end ]] == 0 )
            ++cnt; // this elements wasn't present in current subsequence;
         ++cur[ arr[ end ]];
         continue;
      }
      // if we're here it means that [begin, end] interval contains all distinct elements
      // try to shrink it from behind
      while( cur[ arr[ begin ]] > 1 ) // we have another such element later in the subsequence
      {
         --cur[ arr[ begin ]];
         ++begin;
      }
      // now, compare [begin, end] with the best answer found yet
      if( best == -1 || end - begin < best )
      {
         best = end - begin;
         ansbegin = begin;
         ansend = end;
      }
      // now increment the begin iterator to make cur < k and begin increasing the end iterator again
      --cur[ arr[ begin]];
      ++begin;
      --cnt;
   }

   // output the [ansbegin, ansend] interval as it's the answer to the problem

   cout << ansbegin << ' ' << ansend << endl;
   for( int i = ansbegin; i <= ansend; ++i )
      cout << arr[ i ] << ' ';
   cout << endl;

   return 0;
}
