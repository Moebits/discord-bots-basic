{-# LANGUAGE OverloadedStrings #-}
import Control.Monad (when, void)
import Control.Monad.IO.Class (liftIO)
import System.Environment (lookupEnv)
import qualified Data.Text.IO as TIO
import qualified Data.Text as T
import Discord
import Discord.Types
import qualified Discord.Requests as R
import Configuration.Dotenv (loadFile, defaultConfig)

main :: IO ()
main = do
    loadFile defaultConfig
    token <- lookupEnv "TOKEN"

    case token of
        Nothing -> putStrLn "TOKEN not found in .env file!"
        Just t -> do
            userFacingError <- runDiscord $ def { 
                discordToken = T.pack t, 
                discordOnEvent = eventHandler, 
                discordOnLog = \s -> TIO.putStrLn s >> TIO.putStrLn ""
            }

            TIO.putStrLn userFacingError

eventHandler :: Event -> DiscordHandler ()
eventHandler event = case event of
    Ready _ user _ _ _ _ _ -> do
        liftIO $ putStrLn $ "Logged in as " ++ T.unpack (userName user) ++ "!"
    _ -> return ()