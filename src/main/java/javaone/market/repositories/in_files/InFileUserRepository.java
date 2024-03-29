package javaone.market.repositories.in_files;

import javaone.market.exceptions.UserNotFoundException;
import javaone.market.models.User;
import javaone.market.repositories.in_memory.InMemoryUserRepository;
import javaone.market.repositories.interfaces.UserRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InFileUserRepository implements UserRepository {
    private static int count;
    private final FileObjectSerializer serializer;

    public InFileUserRepository() {
        //userListToFile(); // Эта строка используется только в первый раз.
        serializer = new FileObjectSerializer();
        count = findMaxCount();
    }

    @Override
    public void addUser(User user) {
        user.setId(count++);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
            writer.write(serializer.userToString(user));
            writer.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public User findUserById(Integer userId) throws UserNotFoundException {
        User user = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String str = reader.readLine();
            while(str != null) {
                String[] arr = str.split(",");
                if (Integer.parseInt(arr[0]) == userId) {
                    user = serializer.stringToUser(str);
                    break;
                }
                str = reader.readLine();
            }
            if (user == null) throw new UserNotFoundException(
                    String.format("User with id: %d not found.", userId));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findUserByUsername(String username) throws UserNotFoundException {
        User user = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String str = reader.readLine();
            while(str != null) {
                String[] arr = str.split(",");
                if (arr[1].equals(username)) {
                    user = serializer.stringToUser(str);
                    break;
                }
                str = reader.readLine();
            }
            if (user == null) throw new UserNotFoundException(
                    String.format("User with username: %s not found.", username));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String str = reader.readLine();
            while (str != null) {
                users.add(serializer.stringToUser(str));
                str = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    private int findMaxCount() {
        int maxCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String str = reader.readLine();
            while (str != null) {
                String[] arr = str.split(",");
                int userId = Integer.parseInt(arr[0]);
                if (maxCount < userId) {
                    maxCount = userId;
                }
                str = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ++maxCount;
    }

    private void userListToFile() {
        InMemoryUserRepository repository = new InMemoryUserRepository();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
            for (User user : repository.getAll()) {
                writer.write(serializer.userToString(user));
            }
            writer.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
