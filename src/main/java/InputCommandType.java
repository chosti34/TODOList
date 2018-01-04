public enum InputCommandType {
    ADD_LIST {
        @Override
        public String toString() {
            return "AddList";
        }

        public String getHelpMessage() {
            return "Usage: addlist <ListName>";
        }
    },

    DELETE_LIST {
        @Override
        public String toString() {
            return "DeleteList";
        }

        public String getHelpMessage() {
            return "Usage: deletelist <ListID>";
        }
    },

    ADD_TASK {
        @Override
        public String toString() {
            return "AddTask";
        }

        public String getHelpMessage() {
            return "Usage: addtask <Text> [<ListID>]";
        }
    },

    DELETE_TASK {
        @Override
        public String toString() {
            return "DeleteTask";
        }

        public String getHelpMessage() {
            return "Usage: deletetask <TaskID> <ListID>";
        }
    },

    SHOW {
        @Override
        public String toString() {
            return "Show";
        }

        public String getHelpMessage() {
            return "Usage: show {no parameters here}";
        }
    },

    HELP {
        @Override
        public String toString() {
            return "Help";
        }

        public String getHelpMessage() {
            return "Usage: help {no parameters here}";
        }
    },

    EXIT {
        @Override
        public String toString() {
            return "Exit";
        }

        @Override
        public String getHelpMessage() {
            return "Usage: exit {no parameters here}";
        }
    };

    public abstract String getHelpMessage();
}
