package tictactoe;

public class MediumAI extends AI {
    public MediumAI(Cell.val value) {
        super(value);
    }

    @Override
    public void move(Field field) {
        System.out.println("Making move level \"medium\"");
        for(int x = 0; x < 3; x++) {
            int xs = 0, os = 0, _s = 0;
            for(int y = 0; y < 3; y++) {
                switch(field.cells[x][y].value) {
                    case X -> xs++;
                    case O -> os++;
                    case DEFAULT -> _s++;
                }
            }
            int y = 0;
            if(_s == 1 && (xs == 2 || os == 2)) {
//                System.out.printf("Targeting X = %d %n", x+1);
                while(shouldPut(field, 1+x, 1+y++, value));
                return;
            }
        }
        for(int y = 0; y < 3; y++) {
            int xs = 0, os = 0, _s = 0;
            for(int x = 0; x < 3; x++) {
                switch(field.cells[x][y].value) {
                    case X -> xs++;
                    case O -> os++;
                    case DEFAULT -> _s++;
                }
            }
            int x = 0;
            if(_s == 1 && (xs == 2 || os == 2)) {
//                System.out.printf("Targeting Y = %d %n", y+1);
                while(shouldPut(field, 1+x++, 1+y, value));
                return;
            }
        }
        int xs = 0, os = 0, _s = 0;
        for(int i = 0; i < 3; i++) {
            switch(field.cells[i][i].value) {
                case X -> xs++;
                case O -> os++;
                case DEFAULT -> _s++;
            }
        }
        if(_s == 1 && (xs == 2 || os == 2)) {
//            System.out.printf("Targeting diagonal ltr %n", y+1);
            int x = 0, y = 0;
            while(shouldPut(field, 1+x++, 1+y++, value));
            return;
        }
        xs = os = _s = 0;
        for(int i = 0; i < 3; i++) {
            switch(field.cells[i][2-i].value) {
                case X -> xs++;
                case O -> os++;
                case DEFAULT -> _s++;
            }
        }
        if(_s == 1 && (xs == 2 || os == 2)) {
//            System.out.printf("Targeting diagonal rtl %n", y+1);
            int x = 2, y = 0;
//            int[][] xy = new int[3][2]{{3,1}, {2,2}, {1,3}};
            while(shouldPut(field, 1+x--, 1+y++, value)); //TODO fix potential infinite loop here

            return;
        }
        while(shouldPut(field, r.nextInt(3)+1, r.nextInt(3)+1, value));
    }
}
