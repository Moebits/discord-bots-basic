namespace Structures {
    public interface ICommand {
        Task Run(string[] args);
    }
}
